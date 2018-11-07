package com.serg.bash.util;

import com.serg.bash.monitor.Status;
import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.dto.UrlResponse;
import com.serg.bash.monitor.service.UrlService;
import org.springframework.http.HttpStatus;

//autowired not need :'validator = new Validator()' used
public class Validator {

    private UrlService service;

    private Url url;

    public Validator(UrlService service, Url url) {
        this.service = service;
        this.url = url;
    }

    public void validateResponseCode(int responseCode) {
        Url urlSaved = service.findByName(url.getName()).block();
        if (responseCode != HttpStatus.OK.value()) {
            urlSaved.setStatus(Status.CRITICAL);
        }
        urlSaved.setResponseCode(responseCode);
        service.updateUrl(urlSaved);
    }

    public void validateSubQuery() {
        if (url.getSubQuery() == null) {
            Url urlSaved = service.findByName(url.getName()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved);
        }
    }

    public void validateSizeContent(long responseSize) {
        if (responseSize > url.getMaxResponseSize() || responseSize < url.getMinResponseSize()) {
            Url urlSaved = service.findByName(url.getName()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved);
        }
    }

    public void validateResponseTime(long responseTime) {
        Url urlSaved = service.findByName(url.getName()).block();
        if (responseTime > 1 && responseTime < 5) {
            urlSaved.setStatus(Status.WARNING);
        } else if (responseTime > 5) {
            urlSaved.setStatus(Status.CRITICAL);
        }
        urlSaved.setResponseTime(responseTime);
        service.updateUrl(urlSaved);
    }

    public void initialValidation(UrlResponse url){
        validateResponseCode(url.getResponseCode());
        validateSubQuery();
        validateSizeContent(url.getResponseSize());
        validateResponseTime(url.getResponseTime());
    }
}
