package com.bash.serg.monitor.service;

import com.bash.serg.monitor.entity.impl.Url;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UrlService {

    Flux<Url> findAll();

    Mono<Url> createUrl(Url url);

    Flux<Url> delete(String id);

    Mono<Url> findOne(String id);

    Mono<Url> updateUrl(Url url);

    Flux<Url> findByName(String name);

    Mono<Void> deleteAll();
}
