package com.bash.serg.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableAutoConfiguration
@Configuration
@EnableMongoAuditing
@ComponentScan(basePackages = "com.bash.serg")
@EnableReactiveMongoRepositories(basePackages = "com.bash.serg")
public class WebsiteMonitoringToolServerApp {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteMonitoringToolServerApp.class);
    }
}
