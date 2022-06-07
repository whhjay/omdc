package com.angus.omdc.conn;

import com.angus.omdc.handler.OmdMessageFusion;
import com.angus.omdc.message.OmdMessage;
import com.angus.omdc.message.OmdPacket;
import com.angus.omdc.message.PacketDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

/**
 * netty实现omdc通道连接
 *
 * @author wangjia
 */
@Slf4j
public class NettyOmdcConnector {

    private static final int MAX_PACKET_SIZE = 1500; // 最大封包长度
    private static final int CONNECT_TIMEOUT_MILLIS = 10 * 1000; // 连接超时，10秒
    private static final int READ_TIMEOUT_SEC = 60 * 2; // 读超时，关闭通道触发自动重连
    private static final int[] DELAY_SECONDS = new int[]{1, 2, 3, 3, 3, 10, 20}; // 重连延时表

    private volatile Bootstrap bootstrap;
    private volatile EventLoopGroup eventGroup;
    private volatile boolean running = false;
    private volatile int delaySecondsIndex = 0;

    private final OmdMessageFusion fusion;

    private String host;
    private int port;

    public NettyOmdcConnector(OmdMessageFusion fusion, String host, int port) {
        this.fusion = fusion;
        this.host = host;
        this.port = port;
    }

    public synchronized void start() {
        if (running) {
            log.info("SKIP start " + host + ":" + port + ", it is running.");
            return;
        }
        running = true;
        eventGroup = new NioEventLoopGroup();
        createBootstrapAndConnect();
    }

    public synchronized void stop() {
        running = false;
        try {
            if (eventGroup != null) {
                eventGroup.shutdownGracefully();
            }
            eventGroup = null;
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    public boolean isRunning() {
        return running;
    }

    private void createBootstrapAndConnect() {
        if (!isRunning()) {
            log.info("SKIP createBootstrapAndConnect " + host + ":" + port + ", it is shutdown.");
            return;
        }
        bootstrap = new Bootstrap();

        bootstrap.group(eventGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .handler(new ChannelInitializerHandler());
        log.info("start connecting to... {} : {}", host, port);
        try {
            ChannelFuture connect = bootstrap.connect(host, port);
            EventLoop el = connect.channel().eventLoop();
            connect.addListener(new ReconnectListener(el));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initChannel
     *
     * @author wangjia
     */
    private class ChannelInitializerHandler extends ChannelInitializer {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            ChannelPipeline p = channel.pipeline();
            p.addLast("readTimeoutHandler", new ReadTimeoutHandler(READ_TIMEOUT_SEC));
            p.addLast("reconnect", new DisconnectionHandler());
            p.addLast("frameDecoder",
                    new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_PACKET_SIZE, 0, 2, -2, 0, true));
            p.addLast("packetDecoder", new PacketDecoder());
            p.addLast("packetMessageHandler", new PacketMessageHandler());
        }
    }


    /**
     * reconnect
     *
     * @author wangjia
     */
    private class ReconnectListener implements GenericFutureListener {
        private EventLoop eventLoop;

        public ReconnectListener(EventLoop eventLoop) {
            this.eventLoop = eventLoop;
        }

        public void operationComplete(Future future) throws Exception {
            if (!future.isSuccess()) {
                if (!isRunning()) {
                    log.info("failed to connect " + host + ":" + port + ", it is shutdown.");
                    eventLoop.shutdownGracefully();
                    return;
                }
                long nextRetryDelaySeconds = nextRetryDelaySeconds();
                log.info("failed to connect " + host + ":" + port + ", retry in " + nextRetryDelaySeconds
                        + " seconds ...");
                eventLoop.schedule(() -> createBootstrapAndConnect(), nextRetryDelaySeconds, TimeUnit.SECONDS);
            } else {
                // reset delaySecondsIndex
                delaySecondsIndex = 0;
                log.info("success connected to {}:{}", host, port);
            }
        }
    }

    private long nextRetryDelaySeconds() {
        int index = delaySecondsIndex;
        delaySecondsIndex++;
        if (delaySecondsIndex >= DELAY_SECONDS.length) {
            // 如果一直不成功，使用最后一个超时时间
            delaySecondsIndex = DELAY_SECONDS.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= DELAY_SECONDS.length) {
            index = DELAY_SECONDS.length - 1;
        }
        return DELAY_SECONDS[index];
    }

    /**
     * 连接断开监听处理
     */
    private class DisconnectionHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
            if (!isRunning()) {
                log.info("lost connection from " + host + ":" + port + ", it is shutdown.");
                ctx.close();
                return;
            }
            Channel channel = ctx.channel();
            long nextRetryDelaySeconds = nextRetryDelaySeconds();
            log.info("lost connection from " + host + ":" + port + ", retry in " + nextRetryDelaySeconds
                    + " seconds ...");
            channel.eventLoop().schedule(() -> createBootstrapAndConnect(), nextRetryDelaySeconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 解包后的消息处理
     */
    private class PacketMessageHandler extends SimpleChannelInboundHandler<OmdPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, OmdPacket packet) throws Exception {
            if (packet.getMessages() != null) {
                for (OmdMessage msg : packet.getMessages()) {
                    fusion.messageOffer(msg);
                }
            }
        }
    }

}
