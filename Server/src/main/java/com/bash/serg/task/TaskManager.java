package com.bash.serg.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

@Component
public class TaskManager {

    private static final Logger LOG  = LoggerFactory.getLogger(TaskManager.class);

    private final Executor executor;

    private GetWebsiteTask getWebsiteTask;

    public TaskManager(Executor executor) {
        this.executor = executor;
    }

    @Autowired
    public void setTask(GetWebsiteTask getWebsiteTask) {
        this.getWebsiteTask = getWebsiteTask;
    }

    @PostConstruct
    public void startNewTask() {
        try {
            executor.execute(getWebsiteTask);
        } catch (RejectedExecutionException e) {
            LOG.trace("Couldn't start new task. Server is busy.");
        }
    }
}
