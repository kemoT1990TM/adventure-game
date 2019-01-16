package com.tkjavadev.adventuregame.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class LocationIdImpl implements LocationId, Serializable {
    // == fields ==
    private Long locationId;

    // == constructors ==
    @Autowired
    public LocationIdImpl(@LocId Long locationId) {
        this.locationId = locationId;
    }

    // == methods ==
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getLocationId() {
        return locationId;
    }
}
