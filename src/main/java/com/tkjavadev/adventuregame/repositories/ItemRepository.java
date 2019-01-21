package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
}
