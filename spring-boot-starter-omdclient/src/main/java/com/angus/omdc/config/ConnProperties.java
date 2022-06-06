package com.angus.omdc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "omdc")
public class ConnProperties {
    private String host;
    private List<Integer> ports;

    @Override
    public String toString() {
        return "ConnProperties{" +
                "host='" + host + '\'' +
                ", ports=" + ports +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }
}
