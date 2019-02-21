package com.bash.serg.util;

import com.bash.serg.config.ApplicationProperties;
import com.bash.serg.monitor.Status;
import com.bash.serg.monitor.dto.UrlResponse;
import com.bash.serg.monitor.entity.impl.Url;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

//autowired not need :'validator = new Validator()' used
class StatusValidator {

    private ApplicationProperties properties;

    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    private Url url;

    StatusValidator(Url url) {
        this.url = url;
    }

    private Status validateResponseCode(int responseCode) {
        return responseCode != HttpStatus.OK.value() ? Status.CRITICAL : Status.OK;
    }

    private Status validateSubQuery() {
        if (url.getSubQuery() == null) {
            return Status.CRITICAL;
        }
        return Status.OK;
    }

    private Status validateSizeContent(long responseSize) {
        if ((byte) responseSize > url.getMaxResponseSize() || (byte) responseSize < url.getMinResponseSize()) {
            return Status.CRITICAL;
        }
        return Status.OK;
    }

    private Status validateResponseTime(long responseTime) {
        if (responseTime > 0 && responseTime < properties.warningStatusFrom()) {
            return Status.OK;
        } else if (responseTime > properties.warningStatusFrom() && responseTime < properties.criticalStatusAfter()) {
            return Status.WARNING;
        } else {
            return Status.CRITICAL;
        }
    }

    public Status validate(UrlResponse url) {
        Set<Status> statuses = new HashSet<>();
        statuses.add(validateResponseCode(url.getResponseCode()));
        statuses.add(validateSubQuery());
        statuses.add(validateSizeContent(url.getResponseSize()));
        statuses.add(validateResponseTime(url.getResponseTime()));
        if (statuses.contains(Status.CRITICAL)) {
            return Status.CRITICAL;
        } else if ((!statuses.contains(Status.CRITICAL)) && statuses.contains(Status.WARNING)){
            return Status.WARNING;
        } else {
            return Status.OK;
        }
    }
}
