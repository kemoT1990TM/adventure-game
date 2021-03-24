package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {

    Flux<Item> getItemsByLocId(Long locId);

    Mono<Item> getItemByLocIdAndName(Long locId, String name);
}
