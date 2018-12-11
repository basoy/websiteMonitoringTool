package com.bash.serg.monitor.service.impl;

import com.bash.serg.monitor.dto.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GetWebsiteService {

    @Autowired
    private RestTemplate restTemplate;

    public Future getWebsiteStatus(String url) throws InterruptedException {
        UrlResponse urlResponse = new UrlResponse();
        long startRequestTime = System.currentTimeMillis();
        try {
            String result = restTemplate.getForObject(url, String.class);
            urlResponse.setResponseCode(HttpStatus.OK.value());
            urlResponse.setResponseSize(result.length());
        } catch (HttpStatusCodeException e) {
            urlResponse.setResponseCode(e.getRawStatusCode());
        }
        urlResponse.setResponseTime(System.currentTimeMillis() - startRequestTime);

        return CompletableFuture.completedFuture(urlResponse);
    }
}
