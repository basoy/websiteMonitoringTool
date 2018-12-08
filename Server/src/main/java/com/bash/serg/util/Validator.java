package com.bash.serg.util;

import com.bash.serg.config.ApplicationProperties;
import com.bash.serg.monitor.Status;
import com.bash.serg.monitor.dto.UrlResponse;
import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import org.springframework.http.HttpStatus;

//autowired not need :'validator = new Validator()' used
public class Validator {

    private ApplicationProperties properties;

    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    private UrlService service;

    private Url url;

    public Validator(UrlService service, Url url) {
        this.service = service;
        this.url = url;
    }

    private void validateResponseCode(int responseCode) {
        Url urlSaved = service.findOne(url.getId()).block();
        if (responseCode != HttpStatus.OK.value()) {
            urlSaved.setStatus(Status.CRITICAL);
        }
        urlSaved.setResponseCode(responseCode);
        service.updateUrl(urlSaved).block();//if we return without block() -we get not last information
    }

    private void validateSubQuery() {
        if (url.getSubQuery() == null) {
            Url urlSaved = service.findOne(url.getId()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved).block();
        }
    }

    private void validateSizeContent(long responseSize) {
        if ((byte)responseSize > url.getMaxResponseSize() || (byte)responseSize < url.getMinResponseSize()) {
            Url urlSaved = service.findOne(url.getId()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved).block();
        }
    }

    private void validateResponseTime(long responseTime) {
        Url urlSaved = service.findOne(url.getId()).block();
        if (responseTime > 0 && responseTime <= properties.WARNING_STATUS_FROM()) {
            urlSaved.setStatus(Status.WARNING);
        } else if (responseTime > properties.CRITICAL_STATUS_AFTER()) {
            urlSaved.setStatus(Status.CRITICAL);
        }
        urlSaved.setResponseTime((int)responseTime);
        service.updateUrl(urlSaved).block();
    }

    public void initialValidation(UrlResponse url){
        validateResponseCode(url.getResponseCode());
        validateSubQuery();
        validateSizeContent(url.getResponseSize());
        validateResponseTime(url.getResponseTime());
    }
}
