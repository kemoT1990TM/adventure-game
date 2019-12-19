package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    // == fields ==
    private LocationService locationService;
    private InitVariables initVariables;
    private String itemMessage;
    private String gateMessage;

    // == constructors ==
    @Autowired
    public GameServiceImpl(InitVariables initVariables, LocationService locationService) {
        this.initVariables = initVariables;
        this.locationService = locationService;
    }

    public InitVariables getInitVariables() {
        return initVariables;
    }

    public void setInitVariables(InitVariables initVariables) {
        this.initVariables = initVariables;
    }

    // == methods ==
    /*
    Returns list of available directions for initVariables
     */
    @Override
    public Flux<Gate> getAvailableGates() {
        return locationService.getGatesByLocId(initVariables.getLocationId());
    }

    /*
    Returns description for initVariables
     */
    @Override
    public Mono<String> getDescription() {
        return locationService.getDescriptionByLocId(initVariables.getLocationId());
    }

    /*
    Returns available items for initVariables
     */
    @Override
    public Flux<Item> getAvailableItems() {
        return locationService.getItemsByLocId(initVariables.getLocationId());
    }

    /*
    Returns items in inventory as a String
     */
    @Override
    public String printInventory() {
        return initVariables.printInventory();
    }

    @Override
    public List<String> getInventory() {
        return initVariables.getInventory();
    }

    @Override
    public Mono<Location> getLocation() {
        return locationService.getLocationByLocId(initVariables.getLocationId());
    }

    /*
    Adding item to inventory.
    If item that is going to be added to inventory requires other item the it is checked
    and proper message is printed
    If item is alredy in inventory then message about that is also printed
     */
    @Override
    public void addItemToInventory(Item item) {
        //resets gateMessage
        gateMessage = null;
        if (item.getRequired().equals("NOT") || initVariables.checkInventory(item.getRequired())) {
            if (initVariables.checkInventory(item.getName())) {
                    itemMessage = item.getName() + " -> ALREADY IN INVENTORY";
            } else {
                initVariables.addItem(item.getName());
                    itemMessage = item.getName() + " -> ADDED TO INVENTORY";
            }
        } else {
            itemMessage = "YOU NEED " + item.getRequired() + " TO GET " + item.getName();
        }
    }

    @Override
    public void dropItem(Item item) {
        //resets gateMessage
        gateMessage = null;
        initVariables.dropItem(item.getName());
        itemMessage = item.getName() + " -> DROPPED";
    }

    /*
    Checks if game is over and returning boolean value
     */
    @Override
    public boolean isGameOver() {
        if(initVariables.checkInventory("GOLD")&&initVariables.checkInventory("SILVER")&&
                initVariables.checkInventory("DIAMONDS")&&initVariables.checkInventory("COINS")&&initVariables.checkInventory("JEWELRY")){
            return true;
        }
        if (initVariables.getLocationId() == 80L) {
            return true;
        }
        return false;
    }

    /*
    Adding some randomisation for several directions(gates)
    More complex logic is also implemented like:
    Opening the grate while having the keys in inventory
    Attacking a snake with a bird etc.
     */
    private Long randomizer(Long destination, String requiredItem) {
        double random = Math.random();
        Long loc;
        switch (destination.intValue()) {
            case 300:
                if (random > 0.5) {
                    loc = 5L;
                    break;
                }
                loc = 6L;
                break;
            case 301:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 9L;
                    gateMessage="THE GRATE IS OPEN.";
                    break;
                }
                loc = 23L;
                gateMessage = "THE GRATE IS LOCKED.";
                break;
            case 302:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 8L;
                    gateMessage="THE GRATE IS OPEN.";
                    break;
                }
                loc = 25L;
                gateMessage = "THE GRATE IS LOCKED.";
                log.info("random = {}",random);
                break;
            case 303:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 15L;
                    break;
                }
                loc = 20L;
                gateMessage = "YOU NEED ROD TO GO THERE";
                break;
            case 304:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 14L;
                    break;
                }
                loc = 22L;
                gateMessage = "YOU NEED ROD TO GO THERE";
                break;
            case 306:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 27L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                    break;
                }
                loc = 32L;
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 307:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 29L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                    break;
                }
                loc = 32L;
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 308:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 30L;
                    gateMessage = "THE LITTLE BIRD ATTACKS THE GREEN SNAKE, AND IN AN ASTOUNDING FLURRY DRIVES THE SNAKE AWAY.";
                    break;
                }
                loc = 32L;
                gateMessage = "YOU NEED A BIRD TO KILL THE SNAKE";
                break;
            case 311:
                if (initVariables.checkInventory(requiredItem)) {
                    loc = 8L;
                    gateMessage="THE GRATE IS OPEN.";
                    break;
                }
                loc = 9L;
                gateMessage = "THE GRATE IS LOCKED.";
                break;
            case 312:
                if (random > 0.2) {
                    loc = 65L;
                    break;
                }
                loc = 68L;
                break;
            case 313:
                if (random > 0.2 && random <= 0.5) {
                    loc = 65L;
                    break;
                }
                if (random > 0.5) {
                    loc = 70L;
                    break;
                }
                loc = 39L;
                break;
            case 314:
                if (random > 0.25) {
                    loc = 72L;
                    break;
                }
                loc = 71L;
                break;
            default:
                loc = 80L;
        }
        log.info("location from randomizer = {}",loc);
        return loc;
    }

    /*
    Changes direction and checking if item is required to use specified direction
    Returns new ID of new location
     */
    @Override
    public Long changeDirection(Gate gate) {
       log.error("Dest = {}",gate.getDestId());
                if (gate.getDestId()>=300L) {
                    initVariables.setLocationId(randomizer(gate.getDestId(), gate.getRequired()));
                    initVariables.addVisitedLocation(initVariables.getLocationId());
                } else {
                    if (gate.getRequired().equals("NOT") || initVariables.checkInventory(gate.getRequired())) {
                        initVariables.setLocationId(gate.getDestId());
                        initVariables.addVisitedLocation(initVariables.getLocationId());
                        gateMessage = null;
                    } else {
                        gateMessage = "YOU NEED " + gate.getRequired() + " TO GO THERE";
                    }
                }
        return initVariables.getLocationId();
    }


    /*
    Resets the game
     */
    @Override
    public void reset() {
        gateMessage=null;
        itemMessage=null;
        initVariables.reset();
    }

    /*
    Returns number of visited locations.
     */
    public Integer getVisitedLocations() {
        return initVariables.getVisitedLocations();
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
        initVariables.setLocationId(80L);
    }

    /*
    Returns score of a player
     */
    @Override
    public Integer getScore() {
        return initVariables.getScore();
    }

    /*
    Returns rank of a player considering gathered points
     */
    public String getRank(){
        String rank="You are obviously a rank amateur.  Better luck next time.";
        if(initVariables.getScore()>=35 && initVariables.getScore()<70){
            rank="Your score qualifies you as a novice class adventurer.";
        } else if(initVariables.getScore()>=70 && initVariables.getScore()<100){
            rank="You have achieved the rating \"Experienced Adventurer\".";
        } else if(initVariables.getScore()>=100 && initVariables.getScore()<150){
            rank="You may now consider yourself a \"Seasoned Adventurer\".";
        } else if(initVariables.getScore()>=150 && initVariables.getScore()<200){
            rank="You have reached \"Junior Master\" status.";
        } else if(initVariables.getScore()>=200 && initVariables.getScore()<240){
            rank="Your score qualifies you as a Master Adventurer.";
        } else if(initVariables.getScore()>=240){
            rank= "All of Adventuredom gives tribute to you, Adventure Grandmaster!";
        }
        return rank;
    }
}
