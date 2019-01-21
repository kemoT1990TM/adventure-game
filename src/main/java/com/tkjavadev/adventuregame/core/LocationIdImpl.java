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

    // == constructors ==
    @Autowired
    public LocationIdImpl(@LocId Long locationId) {
        this.locationId = locationId;
        locIds=new HashSet<>();
        locIds.add(locationId);
        this.inventory=new ArrayList<>();
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

    public Long getVisitedLocations(){
        return (long) locIds.size();
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

    public String printInventory(){
        StringBuffer sb=new StringBuffer();
        for(String item:inventory){
            sb.append(item);
            sb.append(", ");
        }
//        if(sb.length()>0) {
//            sb.deleteCharAt(sb.length());
//        }
        String print=sb.toString();
        if(print.length()>1) {
            print.substring(0, print.length() - 2);
        }
        return print;
    }
}
