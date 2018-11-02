package com.serg.bash.monitor.service.impl;

import com.serg.bash.monitor.dto.Url;
import com.serg.bash.monitor.entity.impl.UrlEntity;
import com.serg.bash.monitor.repository.UrlRepository;
import com.serg.bash.monitor.service.UrlService;
import com.serg.bash.util.MonitoringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    @Qualifier("urlRepository")
    UrlRepository repository;

    @Autowired
    MonitoringUtils utils;

    @Override
    public Flux<Url> findAll() {
        List<UrlEntity> block = repository.findAll().collectList().block();
        List <Url> urls = new ArrayList<>();
        for (UrlEntity urlEntity : block) {
            urls.add(utils.fromUrlEntityToUrl(urlEntity));
        }
        return Flux.fromIterable(urls);
    }

    @Override
    public Mono<Url> createUrl(Url url) {
        Mono<UrlEntity> urlEntity = repository.insert(utils.fromUrlToUrlEntity(url));

        return Mono.just(utils.fromUrlEntityToUrl(urlEntity.block()));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Url> findOne(String id) {
        UrlEntity urlEntity = repository.findById(id).block();
        Url url = utils.fromUrlEntityToUrl(urlEntity);
        return Mono.just(url).switchIfEmpty(Mono.error(new Exception("No Url found with Id: " + id)));
    }

    @Override
    public Mono<Url> updateUrl(Url url) {
        UrlEntity destinationEntity = repository.findById(url.getId()).block();
        UrlEntity sourceEntity = utils.fromUrlToUrlEntity(url);
        utils.updateEntity(sourceEntity, destinationEntity);

        return Mono.just(utils.fromUrlEntityToUrl(repository.save(destinationEntity).block()));
    }

    @Override
    public Mono<Url> findByName(String name) {
        UrlEntity urlEntity = repository.findByName(name).blockFirst();
        Url url = utils.fromUrlEntityToUrl(urlEntity);
        return Mono.just(url).switchIfEmpty(Mono.error(new Exception("No Url found with name: " + name)));
    }
}
