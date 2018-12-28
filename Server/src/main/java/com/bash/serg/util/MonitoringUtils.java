package com.bash.serg.util;

import com.bash.serg.config.ApplicationProperties;
import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.WebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
@Import(ApplicationProperties.class)
@Slf4j
public class MonitoringUtils {

    private final ApplicationProperties properties;

    public MonitoringUtils(ApplicationProperties properties){
        this.properties = properties;
    }

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private UrlService service;

    public void addWebsiteToMonitoring(Url url){//todo:optional
        if (url != null) {
            StatusValidator statusValidator = new StatusValidator(url);
            statusValidator.setProperties(getProperties());
            //Blocking(not asynchronous code)???
//            Flux.fromStream(Stream.generate(() -> true).peek((msg) ->
//                    getWebsiteService.getWebsiteStatus(url.getUrl() + url.getSubQuery())
//                        .subscribe(statusValidator::validate)))
//                        .delayElements(Duration.ofMillis(url.getPeriodMonitoring()))
//                        .subscribe();

            Flux.from(Flux.generate(websiteThread -> websiteThread.next(websiteService.getWebsiteStatus(url.getUrl() + url.getSubQuery())
                    .subscribe(urlResponse -> {
                        url.setStatus(statusValidator.validate(urlResponse));
                        url.setResponseCode(urlResponse.getResponseCode());
                        url.setResponseTime((int) urlResponse.getResponseTime());
                        service.updateUrl(url).subscribe();
                    }))))
                    .delayElements(Duration.ofMillis(url.getPeriodMonitoring()))
                    .subscribe();
        }
    }

    public ApplicationProperties getProperties() {
        return properties;
    }
}
