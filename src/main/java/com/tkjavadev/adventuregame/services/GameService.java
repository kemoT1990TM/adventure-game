package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;

import java.util.List;

public interface GameService {

    String getDescription();

    boolean isGameOver();

    Long changeDirection(String direction);

    List<Gate> getAvaliableGates();

    void reset();

    Long getVisitedLocations();

    List<Item> getAvaliableItems();

    String printInventory();

    void addItemToInventory(String name);

    String getItemMessage();

    String getGateMessage();

    void resetMessages();
}
