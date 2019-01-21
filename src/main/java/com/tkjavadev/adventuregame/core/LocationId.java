package com.tkjavadev.adventuregame.core;

import java.util.List;

public interface LocationId {

    void setLocationId(Long locationId);

    Long getLocationId();

    void addVisitedLocation(Long locationId);

    Long getVisitedLocations();

    void reset();

    void addToInventory(String item);

    boolean checkInventory(String item);

    String printInventory();

    List<String> getInventory();
}
