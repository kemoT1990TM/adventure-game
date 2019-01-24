package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.LocationId;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

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
        gateMessage = null;
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

    public Long randomizer(Long destination, String requiredItem) {
        double random = Math.random();
        Long loc;
        log.info("random ={}",random);
        switch (destination.intValue()) {
            case 300:
                loc = 6L;
                if (random > 0.5) {
                    loc = 5L;
                }
                break;
            case 301:
                loc = 23L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 9L;
                    gateMessage="THE GRATE IS OPEN.";
                }
                gateMessage = "THE GRATE IS LOCKED.";
                break;
            case 302:
                loc = 25L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 8L;
                    gateMessage="THE GRATE IS OPEN.";
                }
                gateMessage = "THE GRATE IS LOCKED.";
                log.info("random = {}",random);
                break;
            case 303:
                loc = 20L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 15L;
                }
                gateMessage = "YOU NEED GOLD NUGGET TO GO THERE";
                break;
            case 304:
                loc = 22L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 14L;
                }
                gateMessage = "YOU NEED GOLD NUGGET TO GO THERE";
                break;
            case 306:
                loc = 28L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 32L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                }
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 307:
                loc = 29L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 32L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                }
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 308:
                loc = 30L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 32L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                }
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 311:
                loc = 9L;
                if (locationId.checkInventory(requiredItem)) {
                    loc = 8L;
                    gateMessage="THE GRATE IS OPEN.";
                }
                gateMessage = "THE GRATE IS LOCKED.";
                break;
            case 312:
                loc = 68L;
                if (random > 0.2) {
                    loc = 65L;
                }
                break;
            case 313:
                loc = 39L;
                if (random > 0.2 && random <= 0.5) {
                    loc = 65L;
                }
                if (random > 0.5) {
                    loc = 70L;
                }
                break;
            case 314:
                loc = 71L;
                if (random > 0.25) {
                    loc = 72L;
                }
                break;
            default:
                loc = 80L;
        }
        return loc;
    }

    @Override
    public Long changeDirection(String direction) {
        for (Gate gate : locationService.getLocationById(locationId.getLocationId()).getGates()) {
            if (gate.getDirection().contains(direction)) {
                if (gate.getDestId() >= 300) {
                    locationId.setLocationId(randomizer(gate.getDestId(), gate.getRequired()));
                    locationId.addVisitedLocation(locationId.getLocationId());
                    break;
                } else {
                    if (gate.getRequired().equals("NOT") || locationId.checkInventory(gate.getRequired())) {
                        locationId.setLocationId(gate.getDestId());
                        locationId.addVisitedLocation(locationId.getLocationId());
                        gateMessage = null;
                        break;
                    } else {
                        gateMessage = "YOU NEED " + gate.getRequired() + " TO GO THERE";
                        break;
                    }
                }
            }
        }
        return locationId.getLocationId();
    }


    @Override
    public void reset() {
        locationId.reset();
    }

    public Long getVisitedLocations() {
        return locationId.getVisitedLocations();
    }

    public String getItemMessage() {
        return itemMessage;
    }

    public String getGateMessage() {
        return gateMessage;
    }

    public void resetMessages() {
        itemMessage = null;
    }

    public void exit() {
        locationId.setLocationId(80L);
    }
}
