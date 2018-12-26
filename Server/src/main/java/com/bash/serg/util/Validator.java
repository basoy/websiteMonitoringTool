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

    Validator(UrlService service, Url url) {
        this.service = service;
        this.url = url;
    }

    private void validateResponseCode(int responseCode) {
        service.findOne(url.getId()).subscribe
                (urlResponse -> {
                    urlResponse.setStatus(responseCode != HttpStatus.OK.value() ? Status.CRITICAL : Status.OK);
                    urlResponse.setResponseCode(responseCode);
                    service.updateUrl(urlResponse).subscribe();
                });
    }

    private void validateSubQuery() {
        if (url.getSubQuery() == null) {
            setCriticalStatus();
        }
    }

    private void validateSizeContent(long responseSize) {
        if ((byte) responseSize > url.getMaxResponseSize() || (byte) responseSize < url.getMinResponseSize()) {
            setCriticalStatus();
        }
    }

    private void validateResponseTime(long responseTime) {
        service.findOne(url.getId()).subscribe(urlValidated -> {
            if (responseTime > 0 && responseTime < properties.WARNING_STATUS_FROM()) {
                urlValidated.setStatus(Status.OK);
            } else if (responseTime > properties.WARNING_STATUS_FROM() && responseTime < properties.CRITICAL_STATUS_AFTER()) {
                urlValidated.setStatus(Status.WARNING);
            } else if (responseTime > properties.CRITICAL_STATUS_AFTER()) {
                urlValidated.setStatus(Status.CRITICAL);
            }
            urlValidated.setResponseTime((int) responseTime);
            service.updateUrl(urlValidated).subscribe();
        });
    }

    public void initialValidation(UrlResponse url) {
        validateResponseCode(url.getResponseCode());
        validateSubQuery();
        validateSizeContent(url.getResponseSize());
        validateResponseTime(url.getResponseTime());
    }

    private void setCriticalStatus() {
        service.findOne(url.getId()).subscribe
                (urlWithCritical -> {
                    urlWithCritical.setStatus(Status.CRITICAL);
                    service.updateUrl(urlWithCritical).subscribe();
                });
    }
}
