package com.tkjavadev.adventuregame.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitVariablesImpl implements InitVariables, Serializable {
    // == fields ==
    private Long locationId;
    private Set<Long> locIds;
    private List<String> inventory;
    private Integer score;

    // == constructors ==
    @Autowired
    public InitVariablesImpl() {
        this.locationId = 1L;
        locIds=new HashSet<>();
        locIds.add(locationId);
        this.inventory=new ArrayList<>();
        score=0;
    }

    // == methods ==
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    /*
    Resetting game variables to default
     */
    public void reset(){
        //todo check if value of locationId can be obtained from LocId
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

    /*
    Returns number of visited locations
     */
    public Integer getVisitedLocations(){
        return locIds.size()-1;
    }

    public void addToInventory(String item){
        inventory.add(item);
    }

    /*
    Checking if inventory is containing specified item
     */
    public boolean checkInventory(String item){
        if (inventory.contains(item)){
            return true;
        }
        return false;
    }

    public List<String> getInventory() {
        return inventory;
    }

    /*
    Returns items in inventory as a String
     */
    public String printInventory(){
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

    /*
    Calculates final score
     */
    @Override
    public Integer getScore() {
        Integer itemPoints=0;
        if(checkInventory("FOOD")) itemPoints += 5;
        if(checkInventory("BOOTLE")) itemPoints += 5;
        if(checkInventory("GOLD")) itemPoints += 32;
        if(checkInventory("JEWELRY")) itemPoints += 40;
        if(checkInventory("SILVER")) itemPoints += 28;
        if(checkInventory("DIAMONDS")) itemPoints += 64;
        if(checkInventory("COINS")) itemPoints += 20;
        score=getVisitedLocations()+itemPoints;
        return score;
    }
}
