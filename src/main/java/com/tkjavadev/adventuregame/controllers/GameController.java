package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.exceptions.NotFoundException;
import com.tkjavadev.adventuregame.services.GameService;
import com.tkjavadev.adventuregame.util.AttributeNames;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Slf4j
public class GameController {
    Logger log= LoggerFactory.getLogger(GameController.class);

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
        model.addAttribute(AttributeNames.GATES, gameService.getAvaliableGates());
        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam String direction) {
        log.info("direction = {}", direction);
        gameService.changeDirection(direction);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart() {
        log.info("reset() called");
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName(ViewNames.E404);
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }
}
