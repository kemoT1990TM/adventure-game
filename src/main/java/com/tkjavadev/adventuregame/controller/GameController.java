package com.tkjavadev.adventuregame.controller;

import com.tkjavadev.adventuregame.service.GameService;
import com.tkjavadev.adventuregame.util.AttributeNames;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@Slf4j
public class GameController {
    // == fields ==
    private GameService gameService;

    // == constructors ==
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == methods ==
    @GetMapping(GameMappings.PLAY)
    public String play(Model model) {
        model.addAttribute(AttributeNames.DESCRIPTION, gameService.getDescription());
        model.addAttribute(AttributeNames.EXITS, gameService.getAvaliableExits());
//        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam String direction) {
//        log.info("direction = {}", direction);
        gameService.changeDirection(direction);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart() {
//        log.info("reset() called");
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }
}
