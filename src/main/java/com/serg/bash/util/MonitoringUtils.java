package com.serg.bash.util;

import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.entity.impl.UrlEntity;
import com.serg.bash.task.GetWebsiteTask;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MonitoringUtils {

    private GetWebsiteTask getWebsiteTask;

    @Autowired
    private ModelMapper modelMapper;

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
        new Thread(getWebsiteTask, url.getName()).start();
    }

    public Url fromUrlEntityToUrl(UrlEntity entity){
        return modelMapper.map(entity, Url.class);
    }

    public UrlEntity fromUrlToUrlEntity(Url url){
        return modelMapper.map(url, UrlEntity.class);
    }

    public void updateEntity(UrlEntity sourceEntity, UrlEntity destinationEntity){
        destinationEntity.setStatus(sourceEntity.getStatus());
        destinationEntity.setPeriodMonitoring(sourceEntity.getPeriodMonitoring());
        destinationEntity.setResponseCode(sourceEntity.getResponseCode());
        destinationEntity.setResponseTime(sourceEntity.getResponseTime());
        destinationEntity.setStatus(sourceEntity.getStatus());
        destinationEntity.setSubQuery(sourceEntity.getSubQuery());
        destinationEntity.setUrl(sourceEntity.getUrl());
    }
}
