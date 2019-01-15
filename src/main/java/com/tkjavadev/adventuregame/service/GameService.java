package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Exit;

import java.util.List;

public interface GameService {
    String getDescription();

    boolean isGameOver();

    Long changeDirection(String direction);

    List<Exit> getAvaliableExits();

    void reset();
}
