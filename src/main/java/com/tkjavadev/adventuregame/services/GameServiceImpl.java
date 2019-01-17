package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.LocationId;
import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    // == fields ==
    private LocationService locationService;
    private LocationId locationId;

    // == constructors ==
    @Autowired
    public GameServiceImpl(LocationId locationId, LocationService locationService) {
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
