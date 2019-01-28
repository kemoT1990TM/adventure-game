package com.tkjavadev.adventuregame.core;

import java.util.List;

public interface InitVariables {

    void setLocationId(Long locationId);

    Long getLocationId();

    void addVisitedLocation(Long locationId);

    Integer getVisitedLocations();

    void reset();

    void addToInventory(String item);

    boolean checkInventory(String item);

    String printInventory();

    List<String> getInventory();

    Integer getScore();
}
