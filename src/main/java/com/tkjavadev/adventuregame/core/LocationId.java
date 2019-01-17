package com.tkjavadev.adventuregame.core;

public interface LocationId {

    void setLocationId(Long locationId);

    Long getLocationId();

    void addVisitedLocation(Long locationId);

    Long getVisitedLocations();

    void reset();
}
