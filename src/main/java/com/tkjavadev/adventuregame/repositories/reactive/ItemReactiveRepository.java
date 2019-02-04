package com.tkjavadev.adventuregame.repositories.reactive;

import com.tkjavadev.adventuregame.domain.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemReactiveRepository extends ReactiveMongoRepository<Item,String> {
}
