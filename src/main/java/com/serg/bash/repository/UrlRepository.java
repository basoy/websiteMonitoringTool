package com.serg.bash.repository;

import com.serg.bash.entity.Url;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends ReactiveMongoRepository <Url,String> {
}
