package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.reactive.LocationReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Service
public class LocationServiceImpl implements LocationService, Serializable {

    private final Logger log= LoggerFactory.getLogger(LocationServiceImpl.class);

    // == fields ==
    private final LocationReactiveRepository locationReactiveRepository;

    // == constructors ==
    public LocationServiceImpl(LocationReactiveRepository locationReactiveRepository) {
        this.locationReactiveRepository = locationReactiveRepository;
    }


    // == methods ==
    @Override
    public Mono<Location> getLocationByLocId(Long locId) {
        return locationReactiveRepository.findByLocId(locId);
    }

    /*
    Saving Location to DB
    */
    @Override
    public Mono<Void> saveLocation(Location location) {
        locationReactiveRepository.save(location);
        return Mono.empty();
    }

    public Mono<String> getDescriptionByLocId(Long locId) {
        return locationReactiveRepository.findByLocId(locId).map(Location::getDescription);
    }
}
