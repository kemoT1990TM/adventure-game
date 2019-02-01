package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;

public interface LocationService {

    Location getLocationByLocId(Long locId);

    void saveLocation(Location location);

    Item getItemByLocIdAndName(Long locId, String name);

}
