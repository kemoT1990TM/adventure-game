package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.reactive.LocationReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Service
public class LocationServiceImpl implements LocationService, Serializable {

    private final Logger log= LoggerFactory.getLogger(LocationServiceImpl.class);

    // == fields ==
//    private final LocationRepository locationRepository;
    private final LocationReactiveRepository locationReactiveRepository;

    // == constructors ==
    public LocationServiceImpl(LocationReactiveRepository locationReactiveRepository) {
        this.locationReactiveRepository = locationReactiveRepository;
    }


    // == methods ==
//    /*
//    Returns Location searching by ID
//     */
//    @Override
//    public Location getLocationById(String id) {
//                   Optional<Location> locationOptional = locationRepository.findById(id);
//            if (!locationOptional.isPresent()) {
//            throw new NotFoundException("Location Not Found. For ID value: "+id.toString());
////                System.out.println("Location Not Found. For ID value: " + id.toString());
//            }
//        return locationOptional.get();
//    }

    /*
    Returns Location searching by locID
     */
    @Override
    public Mono<Location> getLocationByLocId(Long locId) {
//        Optional<Location> locationOptional = locationRepository.findByLocId(locId);
//        if (!locationOptional.isPresent()) {
//            throw new NotFoundException("Location Not Found. For ID value: "+locId.toString());
//        }
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

    /*
    Returns Item searching by Location ID and name of Item
    */
    @Override
    public Mono<Item> getItemByLocIdAndName(Long locId, String name) {

//        Optional<Location> locationOptional = locationRepository.findByLocId(locId);
//
//        if (!locationOptional.isPresent()){
//            throw new NotFoundException("Location id not found. Id: " + locId);
//        }
//
//        Location location = locationOptional.get();
//        Optional<Item> itemOptional = location.getItems().stream()
//                .filter(item -> item.getName().equals(name)).findFirst();
//        if(!itemOptional.isPresent()){
//            throw new NotFoundException("Item name not found: " + name);
//        }
        return locationReactiveRepository.findByLocId(locId)
                .flatMapIterable(Location::getItems).filter(item -> item.getName().equals(name)).singleOrEmpty();
    }
}
