package com.bash.serg.monitor.service.impl;

import com.bash.serg.monitor.dto.Url;
import com.bash.serg.monitor.entity.impl.UrlEntity;
import com.bash.serg.monitor.repository.UrlRepository;
import com.bash.serg.monitor.service.UrlService;
import com.bash.serg.util.MonitoringUtils;
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
        return Flux.fromIterable(urls).switchIfEmpty(Mono.
                error(new Exception("No Urls found!")));
    }

    @Override
    public Mono<Url> createUrl(Url url) {
        Mono<UrlEntity> urlEntity = repository.insert(utils.fromUrlToUrlEntity(url));

        return Mono.just(utils.fromUrlEntityToUrl(urlEntity.block()));
    }

    @Override
    public Mono<Void> delete(String id) {

        return repository.deleteById(id).doOnError(Throwable::getMessage);
    }

    @Override
    public Mono<Url> findOne(String id) {
        UrlEntity urlEntity = repository.findById(id).switchIfEmpty(Mono.
                error(new Exception("No Url found with Id: " + id))).block();
        Url url = utils.fromUrlEntityToUrl(urlEntity);

        return Mono.just(url);
    }

    @Override
    public Mono<Url> updateUrl(Url url) {
        String id = url.getId();
        UrlEntity destinationEntity = repository.findById(id).switchIfEmpty(Mono.
                error(new Exception("No Url found with Id: " + id))).block();
        UrlEntity sourceEntity = utils.fromUrlToUrlEntity(url);
        utils.updateEntity(sourceEntity, destinationEntity);

        return Mono.just(utils.fromUrlEntityToUrl(repository.save(destinationEntity).block()));
    }

    @Override
    public Mono<Url> findByName(String name) {
        UrlEntity urlEntity = repository.findByName(name).switchIfEmpty(Mono.
                error(new Exception("No Url found with name: " + name))).blockFirst();
        Url url = utils.fromUrlEntityToUrl(urlEntity);

        return Mono.just(url);
    }

    @Override
    public Mono<Void> deleteAll(){
        return repository.deleteAll();
    }
}
