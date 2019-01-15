package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.domain.Exit;

import java.util.List;

public interface Game {

    List<Exit> getAvaliableExits();

    String getDescription();

    boolean isGameOver();

    Long changeDirection(String direction);

    void reset();
}
