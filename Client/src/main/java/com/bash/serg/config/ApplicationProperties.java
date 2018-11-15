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
    private static final String ADDRESS_SERVER = "address.server";

    public String PROXY_HOSTNAME() {
        return environment.getProperty(PROXY_HOSTNAME, String.class);
    }

    public Integer PROXY_PORT() {
        return environment.getProperty(PROXY_PORT, Integer.class);
    }

    public Boolean USE_PROXY() {
        return environment.getProperty(USE_PROXY, Boolean.class);
    }

    public String ADDRESS_SERVER() {
        return environment.getProperty(ADDRESS_SERVER, String.class);
    }
}
