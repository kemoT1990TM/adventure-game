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
    private Long locationId;

    // == constructors ==
    @Autowired
    public GameImpl(@LocationId Long locationId,LocationService locationService) {
        this.locationId = locationId;
        this.locationService = locationService;
    }

    // == methods ==
//	public void setLocationId() {
//		locationId = 1L;
//		// Random random=new Random();
//		// locationId=random.nextInt(locations.size());
//	}

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public List<Gate> getAvaliableGates() {
        return locationService.getLocationById(locationId).getGates();
    }

    @Override
    public String getDescription() {
        return locationService.getLocationById(locationId).getDescription();
    }

    @Override
    public boolean isGameOver() {
        if (locationId == 141L) {
            return true;
        }
        return false;
    }

    @Override
    public Long changeDirection(String direction) {

        for (Gate gate : locationService.getLocationById(locationId).getGates()) {
            // System.out.println(gate.getDestId()+" "+gate.getDirection());
            if (gate.getDirection().contains(direction)) {
                locationId = gate.getDestId();
                break;
            }
        }
        return locationId;
    }

    @Override
    public void reset() {
        setLocationId(1L);
    }

    public Long getLocationId() {
        return locationId;
    }
}
