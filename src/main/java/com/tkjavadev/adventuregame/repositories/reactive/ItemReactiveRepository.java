package com.tkjavadev.adventuregame.repositories.reactive;

import com.tkjavadev.adventuregame.domain.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ItemReactiveRepository extends ReactiveMongoRepository<Item,String> {
    Flux<Item> findByLocId(Long locId);
}
