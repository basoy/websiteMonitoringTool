package com.bash.serg.monitor.repository;

import com.bash.serg.monitor.entity.impl.Url;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface UrlRepository extends ReactiveMongoRepository <Url,String> {
    Flux<Url> findByName(String name);
}
