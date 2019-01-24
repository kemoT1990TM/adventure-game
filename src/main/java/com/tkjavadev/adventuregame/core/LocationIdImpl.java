package com.tkjavadev.adventuregame.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@SessionScope
public class LocationIdImpl implements LocationId, Serializable {
    // == fields ==
    private Long locationId;
    private Set<Long> locIds;
    private List<String> inventory;
    private Integer score;

    // == constructors ==
    @Autowired
    public LocationIdImpl(@LocId Long locationId) {
        this.locationId = locationId;
        locIds=new HashSet<>();
        locIds.add(locationId);
        this.inventory=new ArrayList<>();
        score=0;
    }

    // == methods ==
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void reset(){
        locationId=1L;
        locIds=new HashSet<>();
        locIds.add(locationId);
        inventory=new ArrayList<>();
    }

    public Long getLocationId() {
        return locationId;
    }

    public void addVisitedLocation(Long locationId){
        locIds.add(locationId);
    }

    public Integer getVisitedLocations(){
        return locIds.size()-1;
    }

    public void addToInventory(String item){
        inventory.add(item);
    }

    public boolean checkInventory(String item){
        if (inventory.contains(item)){
            return true;
        }
        return false;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public synchronized String printInventory(){
        StringBuffer sb=new StringBuffer();
        for(String item:inventory){
            sb.append(item);
            sb.append(", ");
        }
        String print=sb.toString();
        if(print.length()>1) {
           print=print.substring(0, print.length() - 2);
        }
        return print;
    }

    @Override
    public Integer getScore() {
        Integer itemPoints=0;
        if(checkInventory("GOLD")) itemPoints += 32;
        if(checkInventory("JEWELERY")) itemPoints += 40;
        if(checkInventory("SILVER")) itemPoints += 28;
        if(checkInventory("DIAMONDS")) itemPoints += 64;
        if(checkInventory("COINS")) itemPoints += 20;
        score=getVisitedLocations()+itemPoints;
        return score;
    }
}
