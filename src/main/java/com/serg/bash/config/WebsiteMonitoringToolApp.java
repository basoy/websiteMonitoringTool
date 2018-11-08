package com.serg.bash.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableAutoConfiguration
@Configuration
@EnableMongoAuditing
@ComponentScan(basePackages = "com.serg.bash.*")
@EnableReactiveMongoRepositories(basePackages = "com.serg.bash.*")
public class WebsiteMonitoringToolApp {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteMonitoringToolApp.class);
    }
}
