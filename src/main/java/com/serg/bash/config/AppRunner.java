package com.serg.bash.config;

import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.service.UrlService;
import com.serg.bash.monitor.service.impl.GetWebsiteService;
import com.serg.bash.util.MonitoringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        List<Url> urls = urlService.findAll().collectList().block();
        for (Url url : urls) {
            utils.addWebsiteToMonitoring(url);
        }
    }
}

