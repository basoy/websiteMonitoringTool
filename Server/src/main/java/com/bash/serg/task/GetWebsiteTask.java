package com.bash.serg.task;

import com.bash.serg.monitor.dto.UrlResponse;
import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.monitor.service.impl.GetWebsiteService;
import com.bash.serg.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetWebsiteTask implements Runnable {

    @Autowired
    private GetWebsiteService getWebsiteService;

    @Autowired
    private UrlService service;

    @Autowired
    private Url url;

    public GetWebsiteTask(Url url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Validator validator = new Validator(service, url);
            Future website = new CompletableFuture();
            Thread currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted()) {
                String urlFull = url.getUrl() + url.getSubQuery();
                website = getWebsiteService.getWebsiteStatus(urlFull);
                UrlResponse urlResponse = (UrlResponse) website.get();
                TimeUnit.MILLISECONDS.sleep(url.getPeriodMonitoring());
                validator.initialValidation(urlResponse);
            }
            website.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
