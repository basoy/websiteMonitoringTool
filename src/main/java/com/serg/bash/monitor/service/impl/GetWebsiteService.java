package com.serg.bash.monitor.service.impl;

import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.dto.UrlResponse;
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
        UrlResponse urlResponse = new UrlResponse();
        try {
            ResponseEntity <Object>result = restTemplate.getForEntity(url, null);
            urlResponse.setResponseCode(result.getStatusCode().value());
            urlResponse.setResponseSize(result.getHeaders().getContentLength());
        } catch (Exception e) {
            System.out.println(e);
        }

        return CompletableFuture.completedFuture(urlResponse);
    }
}
