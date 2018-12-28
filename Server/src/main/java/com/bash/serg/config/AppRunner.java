package com.bash.serg.config;

import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.WebsiteService;
import com.bash.serg.util.MonitoringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

    @Autowired
    private final WebsiteService websiteService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private MonitoringUtils utils;

    public AppRunner(WebsiteService getWebsiteService) {
        this.websiteService = getWebsiteService;
    }

    @Override
    public void run(String... args) {
        urlService.findAll().
                subscribe(url -> utils.addWebsiteToMonitoring(url));
    }
}

