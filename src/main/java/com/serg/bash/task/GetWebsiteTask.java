package com.serg.bash.task;

import com.serg.bash.monitor.Status;
import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.service.UrlService;
import com.serg.bash.monitor.service.impl.GetWebsiteService;
import com.serg.bash.util.MonitoringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetWebsiteTask implements Runnable {

    @Autowired
    private GetWebsiteService getWebsiteService;

    @Autowired
    private UrlService service;

    @Autowired
    private MonitoringUtils utils;

    @Autowired
    private Url url;

    public GetWebsiteTask(Url url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Future website = new CompletableFuture();
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()){
                String urlFull = url.getUrl() + url.getSubQuery();
                website = getWebsiteService.getWebsiteStatus(urlFull);
                HttpStatus httpStatus = (HttpStatus)website.get();
                validateHttpStatus(httpStatus);
                validateSubQuery();
                System.out.println(new Date() + "[" + currentThread.getName() + "]" + ":" + httpStatus + ":" + urlFull);
            }
            website.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateHttpStatus(HttpStatus httpStatus) {
        if(httpStatus != HttpStatus.OK){
            Url urlSaved = service.findByName(url.getName()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved);
            Thread.currentThread().interrupt();
        }
    }

    private void validateSubQuery(){
        if(url.getSubQuery() == null){
            Url urlSaved = service.findByName(url.getName()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved);
            // Thread.currentThread().interrupt();
        }
    }

    private void validateSizeContent(){
        if(url.getSubQuery() == null){
            Url urlSaved = service.findByName(url.getName()).block();
            urlSaved.setStatus(Status.CRITICAL);
            service.updateUrl(urlSaved);
            // Thread.currentThread().interrupt();
        }
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
