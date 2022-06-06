package com.angus.omdc.example;

import com.angus.omdc.conn.ConnectorManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.angus.omdc")
public class ExampleApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ExampleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        ConnectorManager connectorManager = context.getBean(ConnectorManager.class);
        connectorManager.start();
    }
}
