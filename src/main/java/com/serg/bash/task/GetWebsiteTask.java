package com.serg.bash.task;

import com.serg.bash.entity.impl.Url;
import com.serg.bash.service.impl.GetWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
    private Url url;

    public GetWebsiteTask(Url url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Future website = new CompletableFuture();
            while(!Thread.currentThread().isInterrupted()){
                String urlFull = this.url.getUrl() + this.url.getSubQuery();
                website = getWebsiteService.getWebsiteStatus(urlFull);
                System.out.println(new Date() + "[" + Thread.currentThread().getName() + "]" + ":" + website.get() + ":" + urlFull);
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
