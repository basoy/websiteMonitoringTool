package com.serg.bash.util;

import com.serg.bash.monitor.entity.impl.Url;
import com.serg.bash.task.GetWebsiteTask;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class MonitoringUtils {

    private GetWebsiteTask getWebsiteTask;

    @Lookup
    public GetWebsiteTask getGetWebsiteTask(){
        return null;
    }

    public void deleteWebsiteFromMonitoring(String threadName){
        Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();

        for(Thread thread : setOfThread){
            if(thread.getName().equals(threadName)){
                thread.interrupt();
            }
        }
    }

    public void addWebsiteToMonitoring(Url url) {
        getWebsiteTask = getGetWebsiteTask();
        getWebsiteTask.setUrl(url);
        new Thread(getWebsiteTask, url.getName()).start();
    }
}
