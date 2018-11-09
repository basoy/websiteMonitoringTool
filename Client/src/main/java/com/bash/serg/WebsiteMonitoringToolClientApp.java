package com.bash.serg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@EnableAutoConfiguration
@Configuration
@EnableMongoAuditing
//@ComponentScan(basePackages = "com.serg.bash.*")
//@EnableReactiveMongoRepositories(basePackages = "com.serg.bash.*")
public class WebsiteMonitoringToolClientApp {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteMonitoringToolClientApp.class);
    }
}
