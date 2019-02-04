package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import reactor.core.publisher.Mono;

public interface LocationService {

    Mono<Location> getLocationByLocId(Long locId);

    Mono<Void> saveLocation(Location location);

    Mono<Item> getItemByLocIdAndName(Long locId, String name);

}
