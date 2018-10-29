package com.serg.bash.config;

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

    public String PROXY_HOSTNAME() {
        return environment.getProperty(PROXY_HOSTNAME, String.class);
    }

    public Integer PROXY_PORT() {
        return environment.getProperty(PROXY_PORT, Integer.class);
    }
}
