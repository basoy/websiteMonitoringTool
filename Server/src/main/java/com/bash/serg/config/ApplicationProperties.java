package com.bash.serg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    private final Environment environment;

    public ApplicationProperties(Environment environment) {
        this.environment = environment;
    }

    private static final String PROXY_HOSTNAME = "proxyHostname";
    private static final String PROXY_PORT = "proxyPort";
    private static final String USE_PROXY = "useProxy";
    private static final String THREAD_NAME = "threadName";
    private static final String WARNING_STATUS_FROM = "warningStatusFrom";
    private static final String CRITICAL_STATUS_AFTER = "criticalStatusAfter";

    public String PROXY_HOSTNAME() {
        return environment.getProperty(PROXY_HOSTNAME, String.class);
    }

    public Integer PROXY_PORT() {
        return environment.getProperty(PROXY_PORT, Integer.class);
    }

    public Boolean USE_PROXY() {
        return environment.getProperty(USE_PROXY, Boolean.class);
    }

    public String THREAD_NAME() {
        return environment.getProperty(THREAD_NAME, String.class);
    }

    public Integer WARNING_STATUS_FROM() {
        return environment.getProperty(WARNING_STATUS_FROM, Integer.class);
    }

    public Integer CRITICAL_STATUS_AFTER() {
        return environment.getProperty(CRITICAL_STATUS_AFTER, Integer.class);
    }
}
