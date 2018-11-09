package com.serg.bash.monitor.repository;

import com.serg.bash.monitor.entity.impl.UrlEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface UrlRepository extends ReactiveMongoRepository <UrlEntity,String> {
    Flux<UrlEntity> findByName(String name);
}
