package com.bash.serg.config;

import com.bash.serg.monitor.dto.Url;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.GetWebsiteService;
import com.bash.serg.util.MonitoringUtils;
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

