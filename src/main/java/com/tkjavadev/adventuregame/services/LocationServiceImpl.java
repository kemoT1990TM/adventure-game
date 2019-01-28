package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.exceptions.NotFoundException;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService, Serializable {

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
}
