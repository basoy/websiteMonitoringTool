package com.bash.serg.util;

import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.task.GetWebsiteTask;
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
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for(Thread thread : threads){
            if(thread.getName().equals(threadName)){
                thread.interrupt();
            }
        }
    }

    public void addWebsiteToMonitoring(Url url) {
        getWebsiteTask = getGetWebsiteTask();
        getWebsiteTask.setUrl(url);
        new Thread(getWebsiteTask, "websiteMonitoringTool-" + url.getName()).start();
    }
}
