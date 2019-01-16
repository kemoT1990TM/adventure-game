package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.core.Game;
import com.tkjavadev.adventuregame.core.MessageGenerator;
import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    // == fields ==
    private Game game;
    private MessageGenerator messageGenerator;

    // == constructors ==
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == methods ==
    public String getDescription() {
        return messageGenerator.getDescription();
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public Long changeDirection(String direction) {
        return game.changeDirection(direction);
    }

    public List<Gate> getAvaliableGates() {
        return game.getAvaliableGates();
    }

    public void reset() {
        game.reset();
    }
}
