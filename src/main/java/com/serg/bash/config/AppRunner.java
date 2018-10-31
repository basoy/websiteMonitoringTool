package com.serg.bash.config;

import com.serg.bash.service.UrlService;
import com.serg.bash.service.impl.GetWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private final GetWebsiteService getWebsiteService;

    @Autowired
    private UrlService urlService;

    public AppRunner(GetWebsiteService getWebsiteService) {
        this.getWebsiteService = getWebsiteService;
    }

    @Override
    public void run(String... args) throws Exception {

//        List<Url> urls = urlService.findAll().collectList().block();
//        while (true) {
//            for (Url url : urls) {
//                String subQuery = url.getSubQuery();
//                if (subQuery != null) {
//                    String urlFull = url.getUrl() + subQuery;
//                    CompletableFuture<Object> page1 = getWebsiteService.getWebsiteStatus(urlFull);
//                    System.out.println(new Date() + "[" + Thread.currentThread().getName() + "]"  + ":" + page1.get() + ":" + urlFull);
//                }
//            }
//        }
    }
}
