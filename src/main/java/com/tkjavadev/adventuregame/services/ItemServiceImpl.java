package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.repositories.reactive.ItemReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

    // == fields ==
    private final ItemReactiveRepository itemReactiveRepository;

    // == constructors ==
    public ItemServiceImpl(ItemReactiveRepository itemReactiveRepository) {
        this.itemReactiveRepository = itemReactiveRepository;
    }

    @Override
    public Flux<Item> getItemsByLocId(Long locId) {
        return itemReactiveRepository.findByLocId(locId);
    }

    @Override
    public Mono<Item> getItemByLocIdAndName(Long locId, String name) {
        return itemReactiveRepository.findByLocId(locId)
                .filter(item -> item.getName().equals(name)).singleOrEmpty();
    }
}
