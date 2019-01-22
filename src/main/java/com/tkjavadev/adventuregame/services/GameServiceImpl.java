package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.LocationId;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    // == fields ==
    private LocationService locationService;
    private ItemService itemService;
    private LocationId locationId;
    private String itemMessage;
    private String gateMessage;

    // == constructors ==
    @Autowired
    public GameServiceImpl(LocationId locationId, LocationService locationService, ItemService itemService) {
        this.locationId = locationId;
        this.locationService = locationService;
        this.itemService = itemService;
    }

    // == methods ==
    @Override
    public List<Gate> getAvaliableGates() {
        return locationService.getLocationById(locationId.getLocationId()).getGates();
    }

    @Override
    public String getDescription() {
        return locationService.getLocationById(locationId.getLocationId()).getDescription();
    }

    @Override
    public List<Item> getAvaliableItems() {
        return locationService.getLocationById(locationId.getLocationId()).getItems();
    }

    @Override
    public String printInventory() {
        return locationId.printInventory();
    }


    @Override
    public void addItemToInventory(String name) {
        Item item = itemService.getItemByLocIdAndName(locationId.getLocationId(), name);
        gateMessage=null;
        if (item.getRequired().equals("NOT") || locationId.checkInventory(item.getRequired())) {
            if (locationId.checkInventory(item.getName())) {
                if (item.getName().equals("KEYS")) {
                    itemMessage = item.getName() + " ARE ALREADY IN INVENTORY";
                } else {
                    itemMessage = item.getName() + " IS ALREADY IN INVENTORY";
                }
            } else {
                locationId.addToInventory(item.getName());
                if (item.getName().equals("KEYS")) {
                    itemMessage = item.getName() + " HAVE BEEN ADDED TO INVENTORY";
                } else {
                    itemMessage = item.getName() + " HAS BEEN ADDED TO INVENTORY";
                }
            }
        } else {
            itemMessage = "YOU NEED " + item.getRequired() + " TO GET " + item.getName();
        }
    }

    @Override
    public boolean isGameOver() {
        if (locationId.getLocationId() == 80L) {
            return true;
        }
        return false;
    }

    @Override
    public Long changeDirection(String direction) {
        for (Gate gate : locationService.getLocationById(locationId.getLocationId()).getGates()) {
            if (gate.getRequired().equals("NOT") || locationId.checkInventory(gate.getRequired())) {
                if (gate.getDirection().contains(direction)) {
                    locationId.setLocationId(gate.getDestId());
                    locationId.addVisitedLocation(locationId.getLocationId());
                    gateMessage=null;
                    break;
                }
            } else {
                gateMessage = "YOU NEED " + gate.getRequired() + " TO GO THERE";
            }
        }
        return locationId.getLocationId();
    }


        @Override
        public void reset () {
            locationId.reset();
        }

        public Long getVisitedLocations () {
            return locationId.getVisitedLocations();
        }

        public String getItemMessage () {
            return itemMessage;
        }

    public String getGateMessage () {
        return gateMessage;
    }

        public void resetMessages () {
            itemMessage = null;
        }
    }
