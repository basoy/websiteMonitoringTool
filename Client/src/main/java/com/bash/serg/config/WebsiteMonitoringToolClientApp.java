package com.bash.serg.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.bash.serg")
public class WebsiteMonitoringToolClientApp {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteMonitoringToolClientApp.class);
    }
}
