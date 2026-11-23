package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GameService {

    Mono<String> getDescription();

    boolean isGameOver();

    Long changeDirection(Gate gate);

    Flux<Gate> getAvailableGates();

    void reset();

    Integer getVisitedLocations();

    Flux<Item> getAvailableItems();

    String printInventory();

    List<String> getInventory();

    void addItemToInventory(Item item);

    void dropItem(Item item);

    String getItemMessage();

    String getGateMessage();

    void resetMessages();

    void exit();

    Integer getScore();

    String getRank();

    InitVariables getInitVariables();

    void setInitVariables(InitVariables initVariables);

    Mono<Location> getLocation();
}
