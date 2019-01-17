package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;

import java.util.List;

public interface GameService {

    String getDescription();

    boolean isGameOver();

    Long changeDirection(String direction);

    List<Gate> getAvaliableGates();

    void reset();
}
