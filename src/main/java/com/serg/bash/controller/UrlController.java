package com.serg.bash.controller;

import com.serg.bash.entity.Url;
import com.serg.bash.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public Flux<Url> findAll() {
        log.debug("findAll Url");
        return urlService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Url> createUrl(@RequestBody Url url) {
        log.debug("insert Url");
        return urlService.createUrl(url);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        log.debug("delete Url with id : {}", id);
        return urlService.delete(id);
    }
}
