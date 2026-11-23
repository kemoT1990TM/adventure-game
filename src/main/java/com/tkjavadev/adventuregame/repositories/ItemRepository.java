package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {
}
