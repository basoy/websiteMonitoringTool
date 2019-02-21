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
    private static final String WARNING_STATUS_FROM = "warningStatusFrom";
    private static final String CRITICAL_STATUS_AFTER = "criticalStatusAfter";

    public String getProxyHostname() {
        return environment.getProperty(PROXY_HOSTNAME, String.class);
    }

    public Integer getProxyPort() {
        return environment.getProperty(PROXY_PORT, Integer.class);
    }

    public Boolean isUseProxy() {
        return environment.getProperty(USE_PROXY, Boolean.class);
    }

    public Integer warningStatusFrom() {
        return environment.getProperty(WARNING_STATUS_FROM, Integer.class);
    }

    public Integer criticalStatusAfter() {
        return environment.getProperty(CRITICAL_STATUS_AFTER, Integer.class);
    }
}
