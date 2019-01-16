package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameImpl implements Game {

    // == fields ==
    private LocationService locationService;
    private LocationId locationId;

    // == constructors ==
    @Autowired
    public GameImpl(LocationId locationId, LocationService locationService) {
        this.locationId = locationId;
        this.locationService = locationService;
    }

    // == methods ==
    @Override
    public List<Gate> getAvaliableGates() {
        return locationService.getLocationById(locationId.getLocationId()).getGates();
    }

    @Override
    public String getDescription() {
        return locationService.getLocationById(locationId.getLocationId()).getDescription();
    }

    @Override
    public boolean isGameOver() {
        if (locationId.getLocationId() == 141L) {
            return true;
        }
        return false;
    }

    @Override
    public Long changeDirection(String direction) {

        for (Gate gate : locationService.getLocationById(locationId.getLocationId()).getGates()) {
            // System.out.println(gate.getDestId()+" "+gate.getDirection());
            if (gate.getDirection().contains(direction)) {
                locationId.setLocationId(gate.getDestId());
                break;
            }
        }
        return locationId.getLocationId();
    }

    @Override
    public void reset() {
        locationId.setLocationId(1L);
    }

}
