package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.services.GameService;
import com.tkjavadev.adventuregame.util.AttributeNames;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

@Controller
//@Slf4j
public class GameController {
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
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == methods ==
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder=webDataBinder;
    }

    @GetMapping({"","/","/index"})
    public String start() {
        log.info("Homepage");
        return ViewNames.HOME;
    }

    @GetMapping(GameMappings.PLAY)
    public String play(Model model,WebSession webSession) {
        loadInitVariables(webSession);
        replace(webSession);
        model.addAttribute(AttributeNames.DESCRIPTION, gameService.getDescription());
        model.addAttribute(AttributeNames.GATES, gameService.getAvailableGates());
        model.addAttribute(AttributeNames.ITEMS,gameService.getAvailableItems());
        model.addAttribute(AttributeNames.INVENTORY,gameService.printInventory());
        model.addAttribute(AttributeNames.GET_INVENTORY,gameService.getInventory());
        model.addAttribute(AttributeNames.ITEM_MESSAGE,gameService.getItemMessage());
        model.addAttribute(AttributeNames.GATE_MESSAGE,gameService.getGateMessage());
        log.info("model = {}", model);
//        log.info("play = {}",webSession.getId());
        if (gameService.isGameOver()) {
//            log.info("game over = {}",webSession.getId());
            model.addAttribute(AttributeNames.VISITED,gameService.getVisitedLocations());
            model.addAttribute(AttributeNames.SCORE,gameService.getScore());
            model.addAttribute(AttributeNames.RANK,gameService.getRank());
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.CHANGE)
    public String processMessage(@ModelAttribute("nextGate") Gate gate,WebSession webSession) {
        loadInitVariables(webSession);
        webDataBinder.validate();

        BindingResult bindingResult=webDataBinder.getBindingResult();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.info(objectError.toString()));
            return ViewNames.E404;
        }
        gameService.changeDirection(gate);
        gameService.resetMessages();
        replace(webSession);
//        log.info("process = {}",webSession.getId());
        return GameMappings.REDIRECT_PLAY;
    }

    @PostMapping(GameMappings.ADD)
    public String addItemToInventory(@ModelAttribute("invItem") Item item,WebSession webSession) {
        loadInitVariables(webSession);
        gameService.addItemToInventory(item);
        replace(webSession);
        return GameMappings.REDIRECT_PLAY;
    }

    @PostMapping(GameMappings.DROP)
    public String dropItem(@ModelAttribute("dropItem") Item item, WebSession webSession) {
        loadInitVariables(webSession);
        gameService.dropItem(item);
        replace(webSession);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart(WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("reset() called for = {}", webSession.getId());
        gameService.reset();
        replace(webSession);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.HOME)
    public String home(WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("home() called for = {}", webSession.getId());
        if (gameService.isGameOver()) {
//            log.info("reset() called for = {}", webSession.getId());
            gameService.reset();
            replace(webSession);
        }
        return ViewNames.HOME;
    }

    @GetMapping(GameMappings.EXIT)
    public String exit(Model model,WebSession webSession) {
        loadInitVariables(webSession);
//        log.info("exit() called for = {}",webSession.getId());
        gameService.exit();
        replace(webSession);
        return GameMappings.REDIRECT_PLAY;
    }

    @ModelAttribute("nextGate")
    public Gate nextGateInstance() {
        Gate nextGate = new Gate();
        return nextGate;
    }

    @ModelAttribute("invItem")
    public Item invItemInstance() {
        Item invItem = new Item();
        return invItem;
    }

    @ModelAttribute("dropItem")
    public Item dropItemInstance() {
        Item dropItem = new Item();
        return dropItem;
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
