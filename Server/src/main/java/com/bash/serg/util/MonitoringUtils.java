package com.bash.serg.util;

import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.task.GetWebsiteTask;
import com.bash.serg.task.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MonitoringUtils {

    private GetWebsiteTask getWebsiteTask;

    private TaskManager taskManager;

    @Autowired
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Lookup
    public GetWebsiteTask getGetWebsiteTask(){
        return null;
    }

    public void deleteWebsiteFromMonitoring(String threadName){
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for(Thread thread : threads){
            if(thread.getName().equals("websiteMonitoringTool-" + threadName)){
                thread.interrupt();
            }
        }
    }

    public void deleteAllWebsitesFromMonitoring(){
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for(Thread thread : threads){
            if(thread.getName().contains("websiteMonitoringTool-")){
                thread.interrupt();
            }
        }
    }

    public void addWebsiteToMonitoring(Url url) {
        getWebsiteTask = getGetWebsiteTask();
        getWebsiteTask.setUrl(url);
        taskManager.setTask(getWebsiteTask);
        taskManager.startNewTask();
    }
}
