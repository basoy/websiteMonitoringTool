package com.serg.bash.controller;

import com.serg.bash.entity.Url;
import com.serg.bash.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public Flux<Url> findAll() {
        return urlService.findAll();
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
}
