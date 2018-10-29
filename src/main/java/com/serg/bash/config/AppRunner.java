package com.serg.bash.config;

import com.serg.bash.service.GetWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private final GetWebsiteService getWebsiteService;

    public AppRunner(GetWebsiteService getWebsiteService) {
        this.getWebsiteService = getWebsiteService;
    }

    @Override
    public void run(String... args) throws Exception {

        CompletableFuture<Object> page1 = getWebsiteService.getWebsite("Hello,world");
        CompletableFuture<Object> page2 = getWebsiteService.getWebsite("weather");
        CompletableFuture<Object> page3 = getWebsiteService.getWebsite("pictures");

        System.out.println("--> " + page1.get());
        System.out.println("--> " + page2.get());
        System.out.println("--> " + page3.get());

    }

}
