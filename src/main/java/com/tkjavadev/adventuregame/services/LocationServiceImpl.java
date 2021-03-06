package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.exceptions.NotFoundException;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService, Serializable {

    private final Logger log= LoggerFactory.getLogger(LocationServiceImpl.class);

    // == fields ==
    private LocationRepository locationRepository;

    // == constructors ==
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    // == methods ==
    /*
    Returns Location searching by ID
     */
    @Override
    public Location getLocationById(Long id) {
                   Optional<Location> locationOptional = locationRepository.findById(id);
            if (!locationOptional.isPresent()) {
            throw new NotFoundException("Location Not Found. For ID value: "+id.toString());
//                System.out.println("Location Not Found. For ID value: " + id.toString());
            }
        return locationOptional.get();
    }

    /*
    Saving Location to DB
    */
    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    /*
    Returns Item searching by Location ID and name of Item
    */
    @Override
    public Item getItemByLocIdAndName(Long id, String name) {

        Optional<Location> locationOptional = locationRepository.findById(id);

        if (!locationOptional.isPresent()){
            throw new NotFoundException("Location id not found. Id: " + id);
        }

        Location location = locationOptional.get();
        Optional<Item> itemOptional = location.getItems().stream()
                .filter(item -> item.getName().equals(name)).findFirst();
        if(!itemOptional.isPresent()){
            throw new NotFoundException("Item name not found: " + name);
        }
        return itemOptional.get();
    }
}
