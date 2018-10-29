package com.serg.bash.service;

import com.serg.bash.entity.Url;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UrlService {

    Flux<Url> findAll();

    Mono<Url> createUrl(Url url);

    Mono<Void> delete(String id);

    Mono<Url> findOne(String id);
}
