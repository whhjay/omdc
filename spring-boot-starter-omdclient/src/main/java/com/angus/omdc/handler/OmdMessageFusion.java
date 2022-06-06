package com.angus.omdc.handler;

import com.angus.omdc.message.OmdMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class OmdMessageFusion implements InitializingBean {

    private final int QUEUE_SIZE = 200000; // 设置队列大小，防止内存耗尽
    private BlockingQueue<OmdMessage> messageQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);
    private Thread messageConsumerThread;
    private boolean running = false;
    private int consumerCounts = 0; //消费消息统计
    private int lostCounts = 0;     //丢弃的消息统计
    private long lastTimer = System.currentTimeMillis();

    @Resource
    QuoteHandler quoteHandler;


    public void messageOffer(OmdMessage omdMessage) {
        if (!messageQueue.offer(omdMessage)) {
            lostCounts++;
            log.warn("message queue overflow....................");
        }
    }


    @Override
    public void afterPropertiesSet() {
        running = true;
        messageConsumerThread = new Thread("OmdcMessageConsumer-Worker") {
            @Override
            public void run() {
                log.info("start message consumer thread...");
                while (running) {
                    try {
                        OmdMessage omdMessage = messageQueue.take();
                        if (quoteHandler != null) {
                            quoteHandler.processOmdMessage(omdMessage);
                        }
                        if (System.currentTimeMillis() - lastTimer >= 60000) {
                            log.warn("every mins consumer:" + consumerCounts + ",lost:" + lostCounts + ",qouteTime:" + omdMessage.getSendTime() + ",localTime:" + new Date());
                            lostCounts = 0;
                            consumerCounts = 0;
                            lastTimer = System.currentTimeMillis();
                        }
                        consumerCounts++;
                    } catch (Exception ex) {
                        log.error("message consumer exception", ex);
                    }
                }
            }
        };
        messageConsumerThread.start();
    }
}
