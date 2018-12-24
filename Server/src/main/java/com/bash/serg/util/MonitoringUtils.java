package com.bash.serg.util;

import com.bash.serg.config.ApplicationProperties;
import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.GetWebsiteService;
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
    private GetWebsiteService getWebsiteService;

    @Autowired
    MonitoringUtils utils;

    @Autowired
    private UrlService service;

    public void addWebsiteToMonitoring(Url url){
        if (url != null) {
            Validator validator = new Validator(service, url);
            validator.setProperties(utils.getProperties());
            String urlFull = url.getUrl() + url.getSubQuery();
//            Flux.fromStream(Stream.generate(() -> true).peek((msg) -> getWebsiteService.getWebsiteStatus(urlFull).subscribe(validator::initialValidation)))
//                    .delayElements(Duration.ofMillis(url.getPeriodMonitoring()))
//                    .subscribe();

            Flux.from(Flux.generate(x -> x.next(getWebsiteService.getWebsiteStatus(urlFull).subscribe(validator::initialValidation))))
                    .delayElements(Duration.ofMillis(url.getPeriodMonitoring()))
                    .subscribe();
        }
    }

    public ApplicationProperties getProperties() {
        return properties;
    }
}
