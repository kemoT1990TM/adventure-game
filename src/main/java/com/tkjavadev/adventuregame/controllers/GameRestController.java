package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.services.GameService;
import com.tkjavadev.adventuregame.util.AttributeNames;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/game")
public class GameRestController {
    private final Logger log= LoggerFactory.getLogger(GameController.class);

    // == fields ==
    private GameService gameService;

    private WebDataBinder webDataBinder;

    private synchronized void loadInitVariables(WebSession webSession) {
        if(!webSession.getAttributes().isEmpty()) {
            gameService.setInitVariables((InitVariables) webSession.getAttributes().get(webSession.getId()));
            log.info("loaded variables for = {}",webSession.getId());
        } else {
            this.gameService.reset();
            webSession.getAttributes().put(webSession.getId(), gameService.getInitVariables());
            log.info("new variables for = {}",webSession.getId());
        }
    }

    private synchronized void replace(WebSession webSession){
        webSession.getAttributes().replace(webSession.getId(),gameService.getInitVariables());
    }

    // == constructors ==
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    // == methods ==
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder=webDataBinder;
    }

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
        List<Item> inventoryItems=new ArrayList<>();
        for(String item:gameService.getInventory()){
            Item invItem = new Item();
            invItem.setName(item);
            inventoryItems.add(invItem);
        }
        return inventoryItems;
    }
//
//    @GetMapping("/results")
//    public @ResponseBody Mono<Location> getLocation(WebSession webSession) {
//        loadInitVariables(webSession);
//        replace(webSession);
//        return gameService.;
//    }


    @PostMapping("/change")
    public void changeDirection(@RequestBody Gate gate, WebSession webSession) {
        loadInitVariables(webSession);
//        webDataBinder.validate();
//
//        BindingResult bindingResult=webDataBinder.getBindingResult();
//        if(bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(objectError -> log.info(objectError.toString()));
//        }
        gameService.changeDirection(gate);
        gameService.resetMessages();
        replace(webSession);
//        log.info("process = {}",webSession.getId());
    }

    @PostMapping("/take")
    public void addItemToInventory(@RequestBody Item item, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.addItemToInventory(item);
        replace(webSession);
    }

    @PostMapping("/drop")
    public void dropItem(@RequestBody Item item, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.dropItem(item);
        replace(webSession);
    }

    @GetMapping("/restart")
    public void restart(WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("reset() called for = {}", webSession.getId());
        gameService.reset();
        replace(webSession);
    }

    @GetMapping("/start")
    public void home(WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("home() called for = {}", webSession.getId());
        if (gameService.isGameOver()) {
//            log.info("reset() called for = {}", webSession.getId());
            gameService.reset();
            replace(webSession);
        }
    }

    @GetMapping("/exit")
    public void exit(Model model,WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("exit() called for = {}",webSession.getId());
        gameService.exit();
        replace(webSession);
    }

//    @ModelAttribute("nextGate")
//    public Gate nextGateInstance() {
//        Gate nextGate = new Gate();
//        return nextGate;
//    }
//
//    @ModelAttribute("invItem")
//    public Item invItemInstance() {
//        Item invItem = new Item();
//        return invItem;
//    }
//
//    @ModelAttribute("dropItem")
//    public Item dropItemInstance() {
//        Item dropItem = new Item();
//        return dropItem;
//    }

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
