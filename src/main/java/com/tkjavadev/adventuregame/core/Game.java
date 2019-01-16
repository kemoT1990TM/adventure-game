package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.domain.Gate;

import java.util.List;

public interface Game {

    List<Gate> getAvaliableGates();

    String getDescription();

    boolean isGameOver();

    Long changeDirection(String direction);

    void reset();

}
