package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationService {

    Mono<Location> getLocationByLocId(Long locId);

    Mono<Void> saveLocation(Location location);

    Mono<Item> getItemByLocIdAndName(Long locId, String name);

    Flux<Item> getItemsByLocId(Long locId);

    Flux<Gate> getGatesByLocId(Long locId);

    Mono<String> getDescriptionByLocId(Long locId);

}
