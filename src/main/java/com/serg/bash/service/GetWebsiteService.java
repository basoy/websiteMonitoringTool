package com.serg.bash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GetWebsiteService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<Object> getWebsite(String subQuery) throws InterruptedException {
        HttpStatus statusCode = null;
        String url = String.format("https://www.google.com/search?q=%s", subQuery);
        try {
            ResponseEntity<Object> result = restTemplate.getForEntity(url, null);
            statusCode = result.getStatusCode();
        } catch (Exception e) {
            System.out.println(e);
        }

        return CompletableFuture.completedFuture(statusCode);
    }
}
