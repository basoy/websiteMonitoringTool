package com.serg.bash.monitor.service;

import com.serg.bash.monitor.entity.impl.Url;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UrlService {

    Flux<Url> findAll();

    Mono<Url> createUrl(Url url);

    Mono<Void> delete(String id);

    Mono<Url> findOne(String id);
}
