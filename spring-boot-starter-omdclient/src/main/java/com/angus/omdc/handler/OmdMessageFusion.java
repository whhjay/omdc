package com.angus.omdc.handler;

import com.angus.omdc.message.OmdMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class OmdMessageFusion implements InitializingBean {

    private final int QUEUE_SIZE = 200000; // 设置队列大小，防止内存耗尽
    private BlockingQueue<OmdMessage> messageQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);
    private Thread messageConsumerThread;
    private volatile boolean running = false;

    @Resource
    QuoteHandler quoteHandler;


    public void messageOffer(OmdMessage omdMessage) {
        if (!messageQueue.offer(omdMessage)) {
            log.warn("message queue overflow....................");
        }
    }


    @Override
    public void afterPropertiesSet() {
        running = true;
        messageConsumerThread = new Thread("OmdcMessageConsumer-Worker") {
            @SneakyThrows
            @Override
            public void run() {
                log.info("start message consumer thread...");
                while (running) {
                    OmdMessage omdMessage = messageQueue.take();
                    if (quoteHandler != null) {
                        quoteHandler.processOmdMessage(omdMessage);
                    }
                }
                log.info("end message consumer thread...");
            }
        };
        messageConsumerThread.start();
    }

    public void stop() {
        this.running = false;
    }
}
