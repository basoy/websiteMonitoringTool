package com.bash.serg.config;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Import(ApplicationProperties.class)
@Configuration
public class ProxyCustomizer implements RestTemplateCustomizer {

    private final ApplicationProperties properties;

    public ProxyCustomizer(ApplicationProperties properties){
        this.properties = properties;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        if(properties.isUseProxy()) {
            final String proxyUrl = properties.getProxyHostname();
            final int port = properties.getProxyPort();

            HttpHost proxy = new HttpHost(proxyUrl, port);
            HttpClient httpClient = HttpClientBuilder.create().setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {
                @Override
                protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context)
                        throws HttpException {
                    return super.determineProxy(target, request, context);
                }
            }).build();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        }
    }
}