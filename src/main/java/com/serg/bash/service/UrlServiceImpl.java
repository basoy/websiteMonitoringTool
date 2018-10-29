package com.serg.bash.service;

import com.serg.bash.entity.Url;
import com.serg.bash.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlRepository repository;

    @Override
    public Flux<Url> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Url> createUrl(Url url) {
        return repository.insert(url);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Url> findOne(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new Exception("No Url found with Id: " + id)));
    }
}
