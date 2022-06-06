package com.angus.omdc.config;

import com.angus.omdc.conn.ConnectorManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConnProperties.class)
public class ClientAutoConfiguration {
    private ConnProperties connProperties;

    //通过构造方法注入配置属性对象clientProperties
    public ClientAutoConfiguration(ConnProperties connProperties) {
        this.connProperties = connProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ConnectorManager etaClient() {
        System.out.println("\033[1;32m :: omdc client SDK ::        \033[0m     (v1.0.0) \n");
        return new ConnectorManager(this.connProperties);
    }

}
