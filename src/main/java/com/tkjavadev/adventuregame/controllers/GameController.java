package com.tkjavadev.adventuregame.controllers;

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

@Controller
//@Slf4j
public class GameController {
    private final Logger log= LoggerFactory.getLogger(GameController.class);

    // == fields ==
    private GameService gameService;

    private WebDataBinder webDataBinder;

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
    public String play(Model model) {
        model.addAttribute(AttributeNames.DESCRIPTION, gameService.getDescription());
        model.addAttribute(AttributeNames.GATES, gameService.getAvailableGates());
        model.addAttribute(AttributeNames.ITEMS,gameService.getAvailableItems());
        model.addAttribute(AttributeNames.INVENTORY,gameService.printInventory());
        model.addAttribute(AttributeNames.ITEM_MESSAGE,gameService.getItemMessage());
        model.addAttribute(AttributeNames.GATE_MESSAGE,gameService.getGateMessage());
        log.info("model = {}", model);
        if (gameService.isGameOver()) {
            model.addAttribute(AttributeNames.VISITED,gameService.getVisitedLocations());
            model.addAttribute(AttributeNames.SCORE,gameService.getScore());
            model.addAttribute(AttributeNames.RANK,gameService.getRank());
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.CHANGE)
    public String processMessage(@ModelAttribute("nextGate") Gate gate) {

        webDataBinder.validate();

        BindingResult bindingResult=webDataBinder.getBindingResult();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.info(objectError.toString()));
            return ViewNames.E404;
        }
        log.info("destination = {}", gate.getDestId());
        log.info("required = {}", gate.getRequired());
        gameService.changeDirection(gate);
        gameService.resetMessages();
        return GameMappings.REDIRECT_PLAY;
    }

    @PostMapping(GameMappings.ADD)
    public String addItemToInventory(@ModelAttribute("invItem") Item item) {
        log.info("item = {}", item.getName());
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

//    @ModelAttribute("gate")
//    public Gate defaultGateInstance() {
//        Gate gate = new Gate();
//        return gate.getInstance();
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
