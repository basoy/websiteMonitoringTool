package com.bash.serg.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class AppConfiguration {

    @Autowired
    ProxyCustomizer proxyCustomizer;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        proxyCustomizer.customize(restTemplate);
        return restTemplate;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
