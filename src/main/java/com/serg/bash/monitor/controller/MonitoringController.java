package com.serg.bash.monitor.controller;

import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.service.UrlService;
import com.serg.bash.util.MonitoringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class MonitoringController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private MonitoringUtils utils;

    @GetMapping
    public Flux<Url> findAll() {
        return urlService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Url> createUrl(@RequestBody Url url) {
        utils.addWebsiteToMonitoring(url);
        return urlService.createUrl(url);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        utils.deleteWebsiteFromMonitoring(urlService.findOne(id).block().getName());
        return urlService.delete(id);
    }
}
