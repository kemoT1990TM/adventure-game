package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Location;

public interface LocationService {

    Location getLocationById(Long id);

    void saveLocation(Location location);
}
