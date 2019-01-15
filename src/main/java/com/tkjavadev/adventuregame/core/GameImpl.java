package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.domain.Exit;
import com.tkjavadev.adventuregame.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@SessionScope
@Component
public class GameImpl implements Game {

    // == fields ==
    private LocationService locationService;
    private Long locationID = 1L;

    // == constructors ==
    @Autowired
    public GameImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    // == methods ==
//	public void setLocationID() {
//		locationID = 1L;
//		// Random random=new Random();
//		// locationID=random.nextInt(locations.size());
//	}

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    @Override
    public List<Exit> getAvaliableExits() {
        return locationService.getLocationById(locationID).getExits();
    }

    @Override
    public String getDescription() {
        return locationService.getLocationById(locationID).getDescription();
    }

    @Override
    public boolean isGameOver() {
        if (locationID == 141L) {
            return true;
        }
        return false;
    }

    @Override
    public Long changeDirection(String direction) {

        for (Exit exit : locationService.getLocationById(locationID).getExits()) {
            // System.out.println(exit.getDestId()+" "+exit.getDirection());
            if (exit.getDirection().contains(direction)) {
                locationID = exit.getDestId();
                break;
            }
        }
        return locationID;
    }

    @Override
    public void reset() {
        setLocationID(1L);
    }

    public Long getLocationID() {
        return locationID;
    }
}
