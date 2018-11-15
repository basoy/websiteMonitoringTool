package com.bash.serg.config;

import com.bash.serg.monitor.dto.Url;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.GetWebsiteService;
import com.bash.serg.util.MonitoringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

    @Autowired
    private final GetWebsiteService getWebsiteService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private MonitoringUtils utils;

    public AppRunner(GetWebsiteService getWebsiteService) {
        this.getWebsiteService = getWebsiteService;
    }

    @Override
    public void run(String... args) {
        try {
            Flux<Url> all = urlService.findAll();
            List<Url> urls = all.collectList().block();
            for (Url url : urls) {
                utils.addWebsiteToMonitoring(url);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

