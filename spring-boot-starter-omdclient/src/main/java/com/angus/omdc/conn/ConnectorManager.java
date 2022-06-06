package com.angus.omdc.conn;

import com.angus.omdc.config.ConnProperties;
import com.angus.omdc.handler.OmdMessageFusion;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConnectorManager {

    private static Map connectorCache = new ConcurrentHashMap<Long, NettyOmdcConnector>();

    @Resource
    OmdMessageFusion omdMessageFusion;

    private ConnProperties connProperties;

    public ConnectorManager(ConnProperties connProperties) {
        this.connProperties = connProperties;
        for (int i = 0; i < connProperties.getPorts().size(); i++) {
            int port = connProperties.getPorts().get(i);
            NettyOmdcConnector nettyOmdcConnector = new NettyOmdcConnector(omdMessageFusion, connProperties.getHost(), port);
            connectorCache.put(port, nettyOmdcConnector);
        }
    }

    public void start() {
        for (int i = 0; i < connProperties.getPorts().size(); i++) {
            int port = connProperties.getPorts().get(i);
            NettyOmdcConnector nettyOmdcConnector = (NettyOmdcConnector) connectorCache.get(port);
            if (nettyOmdcConnector != null)
                nettyOmdcConnector.start(omdMessageFusion);
        }
    }

    public void stop() {
        for (int i = 0; i < connProperties.getPorts().size(); i++) {
            int port = connProperties.getPorts().get(i);
            NettyOmdcConnector nettyOmdcConnector = (NettyOmdcConnector) connectorCache.get(port);
            if (nettyOmdcConnector != null)
                nettyOmdcConnector.stop();
        }
    }
}
