package com.bash.serg.monitor.service.impl;

import com.bash.serg.monitor.entity.impl.Url;
import com.bash.serg.monitor.repository.UrlRepository;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.util.MonitoringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    @Qualifier("urlRepository")
    UrlRepository repository;

    @Autowired
    MonitoringUtils utils;

    @Override
    public Flux<Url> findAll() {
        return repository.findAll().switchIfEmpty(Flux.
                error(new Exception("No Urls found!")));
    }

    @Override
    public Mono<Url> createUrl(Url url) {
        utils.addWebsiteToMonitoring(url);

        return repository.insert(url);
    }

    @Override
    public Flux<Url> delete(String name) {
        utils.deleteWebsiteFromMonitoring(name);

        return repository.deleteByName(name).doOnError(Throwable::getMessage);
    }

    @Override
    public Mono<Url> findOne(String id) {
        return repository.findById(id).switchIfEmpty(Mono.
                error(new Exception("No Url found with Id: " + id)));
    }

    @Override
    public Mono<Url> updateUrl(Url sourceUrl) {

        return repository.save(sourceUrl);
    }

    @Override
    public Flux<Url> findByName(String name) {
        return repository.findByName(name).switchIfEmpty(Mono.
                error(new Exception("No Url found with name: " + name)));
    }

    @Override
    public Mono<Void> deleteAll(){
        utils.deleteAllWebsitesFromMonitoring();

        return repository.deleteAll();
    }
}
