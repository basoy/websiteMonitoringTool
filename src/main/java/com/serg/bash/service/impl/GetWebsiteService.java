package com.serg.bash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GetWebsiteService {

    @Autowired
    private RestTemplate restTemplate;

    public Future getWebsiteStatus(String url) throws InterruptedException {
        HttpStatus statusCode = null;
        try {
            ResponseEntity result = restTemplate.getForEntity(url, null);
            statusCode = result.getStatusCode();
        } catch (Exception e) {
            System.out.println(e);
        }

        return CompletableFuture.completedFuture(statusCode);
    }
}
