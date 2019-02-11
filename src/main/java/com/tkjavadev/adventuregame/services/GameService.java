package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<String> getDescription();

    boolean isGameOver();

    Long changeDirection(Gate gate);

    Flux<Gate> getAvailableGates();

    void reset();

    Integer getVisitedLocations();

    Flux<Item> getAvailableItems();

    String printInventory();

    void addItemToInventory(Item item);

    String getItemMessage();

    String getGateMessage();

    void resetMessages();

    void exit();

    Integer getScore();

    String getRank();
}
