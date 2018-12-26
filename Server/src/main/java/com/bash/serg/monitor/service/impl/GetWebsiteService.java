package com.bash.serg.monitor.service.impl;

import com.bash.serg.monitor.dto.UrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;

@Service
public class GetWebsiteService {

    @Autowired
    private RestTemplate restTemplate;

    public Mono<UrlResponse> getWebsiteStatus(String url) {
        UrlResponse urlResponse = new UrlResponse();
        long startRequestTime = System.currentTimeMillis();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.ALL));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            URI u = new URI(url);
            u = new URI(u.getScheme(), u.getUserInfo(), InetAddress.getByName(u.getHost()).getHostAddress(),
                    u.getPort(), u.getPath(), u.getQuery(), u.getFragment());

            ResponseEntity<String> exchange = restTemplate.exchange(u.toString(), HttpMethod.GET, entity, String.class);
            urlResponse.setResponseCode(HttpStatus.OK.value());
            urlResponse.setResponseSize(exchange.getBody().length());
        } catch (URISyntaxException | UnknownHostException e) {
            e.printStackTrace();
        } catch (HttpStatusCodeException e) {
            urlResponse.setResponseCode(e.getRawStatusCode());
        }
        urlResponse.setResponseTime(System.currentTimeMillis() - startRequestTime);
        return Mono.just(urlResponse);
    }
}
