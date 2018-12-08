package com.bash.serg.util;

import com.bash.serg.monitor.Status;
import com.bash.serg.monitor.dto.UrlResponse;
import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import org.springframework.http.HttpStatus;

//autowired not need :'validator = new Validator()' used
public class Validator {

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
        if (responseSize > url.getMaxResponseSize() || responseSize < url.getMinResponseSize()) {
            Url urlSaved = service.findOne(url.getId()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved).block();
        }
    }

    private void validateResponseTime(long responseTime) {
        Url urlSaved = service.findOne(url.getId()).block();
        if (responseTime > 1 && responseTime <= 5) {
            urlSaved.setStatus(Status.WARNING);
        } else if (responseTime > 5) {
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
