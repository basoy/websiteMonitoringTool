package com.bash.serg.config;

import com.bash.serg.dto.Url;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.bash.serg")
@Import(ApplicationProperties.class)
public class WebsiteMonitoringToolClientApp {

    private final ApplicationProperties properties;

    public WebsiteMonitoringToolClientApp(ApplicationProperties properties){
        this.properties = properties;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteMonitoringToolClientApp.class, args);

    }

    @Bean
    WebClient client() {
        return WebClient.create(properties.ADDRESS_SERVER());
    }

    @Bean
    CommandLineRunner demo(WebClient client) {
        return args ->  {
          client
                  .get()
                  .uri("")
                  .accept(MediaType.TEXT_EVENT_STREAM)
                  .exchange()
                  .flatMapMany(cr->cr.bodyToFlux(Url.class))
                  .subscribe(System.out::println) ;
        };
    }
}
