package com.bash.serg.monitor.controller;

import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/")
public class MonitoringController {

    @Autowired
    private UrlService urlService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:8084")//for testing server and client from one place
    public Mono<List<Url>> findAll() {
        return urlService.findAll().collectList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Url> createUrl(@RequestBody Url url) {
        return urlService.createUrl(url);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return urlService.delete(id);
    }

    @DeleteMapping("/delete/all")
    public Mono<Void> All() {
        return urlService.deleteAll();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Url> updateUrl(@RequestBody Url url) {
        return urlService.updateUrl(url);
    }
}
