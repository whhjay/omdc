package com.angus.omdc.conn;

import com.angus.omdc.config.ConnProperties;
import com.angus.omdc.handler.OmdMessageFusion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ConnectorManager {

    private static Map connectorCache = new ConcurrentHashMap<Long, NettyOmdcConnector>();

    @Resource
    OmdMessageFusion omdMessageFusion;

    private ConnProperties connProperties;

    public ConnectorManager(ConnProperties connProperties) {
        this.connProperties = connProperties;
    }

    public void start() {
        for (int i = 0; i < this.connProperties.getPorts().size(); i++) {
            int port = this.connProperties.getPorts().get(i);
            NettyOmdcConnector nettyOmdcConnector = new NettyOmdcConnector(omdMessageFusion, this.connProperties.getHost(), port);
            this.connectorCache.put(port, nettyOmdcConnector);
            nettyOmdcConnector.start();
        }
    }

    @PreDestroy
    private void shutdown() {
        omdMessageFusion.stop();
        for (int i = 0; i < connProperties.getPorts().size(); i++) {
            int port = connProperties.getPorts().get(i);
            NettyOmdcConnector nettyOmdcConnector = (NettyOmdcConnector) connectorCache.get(port);
            if (nettyOmdcConnector != null) {
                nettyOmdcConnector.stop();
            }
        }
        log.info("shutdown all connector");
    }
}
