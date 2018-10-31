package com.serg.bash.monitor.repository;

import com.serg.bash.monitor.entity.impl.Url;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends ReactiveMongoRepository <Url,String> {
}
