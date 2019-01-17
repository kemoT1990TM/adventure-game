package com.tkjavadev.adventuregame.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class LocationIdImpl implements LocationId, Serializable {
    // == fields ==
    private Long locationId;
    private Set<Long> locIds=new HashSet<>();

    // == constructors ==
    @Autowired
    public LocationIdImpl(@LocId Long locationId) {
        this.locationId = locationId;
        locIds.add(locationId);
    }

    // == methods ==
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void reset(){
        locationId=1L;
        locIds=new HashSet<>();
        locIds.add(locationId);
    }

    public Long getLocationId() {
        return locationId;
    }

    public void addVisitedLocation(Long locationId){
        locIds.add(locationId);
    }

    public Long getVisitedLocations(){
        return (long) locIds.size();
    }
}
