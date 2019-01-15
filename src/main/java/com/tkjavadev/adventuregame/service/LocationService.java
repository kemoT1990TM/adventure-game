package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Location;

public interface LocationService {

    Location getLocationById(Long id);

    void saveLocation(Location location);
}
