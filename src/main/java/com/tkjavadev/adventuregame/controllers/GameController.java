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
    private final Logger log= LoggerFactory.getLogger(GameController.class);

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
        model.addAttribute(AttributeNames.VISITED,gameService.getVisitedLocations());
        model.addAttribute(AttributeNames.ITEMS,gameService.getAvaliableItems());
        model.addAttribute(AttributeNames.INVENTORY,gameService.printInventory());
        model.addAttribute(AttributeNames.ITEM_MESSAGE,gameService.getItemMessage());
        model.addAttribute(AttributeNames.GATE_MESSAGE,gameService.getGateMessage());
        model.addAttribute(AttributeNames.SCORE,gameService.getScore());
        model.addAttribute(AttributeNames.RANK,gameService.getRank());
        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.CHANGE)
    public String processMessage(@RequestParam String direction) {
        log.info("direction = {}", direction);
        gameService.changeDirection(direction);
        gameService.resetMessages();
        return GameMappings.REDIRECT_PLAY;
    }

    @PostMapping(GameMappings.ADD)
    public String addItemToInventory(@RequestParam String item) {
        log.info("item = {}", item);
        gameService.addItemToInventory(item);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart() {
        log.info("reset() called");
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.HOME)
    public String home() {
        log.info("home() called");
        if (gameService.isGameOver()) {
            log.info("reset() called");
            gameService.reset();
        }
        return ViewNames.HOME;
    }

    @GetMapping(GameMappings.EXIT)
    public String exit(Model model) {
        log.info("exit() called");
        gameService.exit();
        model.addAttribute(AttributeNames.DESCRIPTION, gameService.getDescription());
        model.addAttribute(AttributeNames.VISITED,gameService.getVisitedLocations());
        model.addAttribute(AttributeNames.SCORE,gameService.getScore());
        model.addAttribute(AttributeNames.RANK,gameService.getRank());
        return ViewNames.GAME_OVER;
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
