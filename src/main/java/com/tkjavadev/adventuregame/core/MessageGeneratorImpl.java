package com.tkjavadev.adventuregame.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == methods ==
    @Override
    public String getDescription(){
        return game.getDescription();
    }
}
