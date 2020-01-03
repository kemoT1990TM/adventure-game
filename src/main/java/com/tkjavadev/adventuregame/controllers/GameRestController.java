package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameRestController {
    private final Logger log = LoggerFactory.getLogger(GameController.class);

    // == fields ==
    private GameService gameService;

    private synchronized void loadInitVariables(WebSession webSession) {
        if (!webSession.getAttributes().isEmpty()) {
            gameService.setInitVariables((InitVariables) webSession.getAttributes().get(webSession.getId()));
            log.info("loaded variables for = {}", webSession.getId());
        } else {
            this.gameService.reset();
            webSession.getAttributes().put(webSession.getId(), gameService.getInitVariables());
            log.info("new variables for = {}", webSession.getId());
        }
    }

    private synchronized void replace(WebSession webSession) {
        webSession.getAttributes().replace(webSession.getId(), gameService.getInitVariables());
    }

    // == constructors ==
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    // == methods ==
    @GetMapping("/gates")
    public @ResponseBody Flux<Gate> getGates(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        return gameService.getAvailableGates();
    }

    @GetMapping("/items")
    public @ResponseBody Flux<Item> getItems(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        return gameService.getAvailableItems();
    }

    @GetMapping("/location")
    public @ResponseBody Mono<Location> getLocation(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        return gameService.getLocation();
    }

    @GetMapping("/inventory")
    public @ResponseBody List<Item> getInventory(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        List<Item> inventoryItems = new ArrayList<>();
        for (String item : gameService.getInventory()) {
            Item invItem = new Item();
            invItem.setName(item);
            inventoryItems.add(invItem);
        }
        return inventoryItems;
    }

    @GetMapping("/results")
    public @ResponseBody List<String> getResults(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        List<String> results = new ArrayList<>();
        results.add(gameService.getScore().toString());
        results.add(gameService.getVisitedLocations().toString());
        results.add(gameService.getRank());
        return results;
    }

    @GetMapping("/item/message")
    public @ResponseBody Map getItemMessage(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        return  Collections.singletonMap("message", gameService.getItemMessage());
    }

    @GetMapping("/gate/message")
    public @ResponseBody Map getGateMessage(WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        return Collections.singletonMap("message", gameService.getGateMessage());
    }


    @PostMapping("/gate/change")
    public void changeDirection(@RequestBody Gate gate, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.changeDirection(gate);
        gameService.resetMessages();
        replace(webSession);
    }

    @PostMapping("/item/take")
    public void addItemToInventory(@RequestBody Item item, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.addItemToInventory(item);
        replace(webSession);
    }

    @PostMapping("/item/drop")
    public void dropItem(@RequestBody Item item, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.dropItem(item);
        replace(webSession);
    }

    @GetMapping("/restart")
    public void restart(WebSession webSession) {
        loadInitVariables(webSession);
        log.info("reset() called for = {}", webSession.getId());
        gameService.reset();
        replace(webSession);
    }

    @GetMapping("/start")
    public void start(WebSession webSession) {
        loadInitVariables(webSession);
        log.info("start() called for = {}", webSession.getId());
        if (gameService.isGameOver()) {
            log.info("reset() called for = {}", webSession.getId());
            gameService.reset();
            replace(webSession);
        }
    }

    @GetMapping("/exit")
    public void exit(WebSession webSession) {
        loadInitVariables(webSession);
        log.info("exit() called for = {}", webSession.getId());
        gameService.exit();
        replace(webSession);
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleNotFound(Exception exception){
//        log.error("Handling not found exception");
//        log.error(exception.getMessage());
//
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName(ViewNames.E404);
//        modelAndView.addObject("exception",exception);
//
//        return modelAndView;
//    }
}
