package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.services.GameService;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Ignore
public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/templates/");
//        viewResolver.setSuffix(".html");

        gameController = new GameController(gameService);
//        mockMvc = MockMvcBuilders.standaloneSetup(gameController)
//                .setViewResolvers(viewResolver).build();

         mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void play() throws Exception {
        mockMvc.perform(get("/" + GameMappings.PLAY))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.PLAY));

        when(gameService.isGameOver()).thenReturn(true);

        mockMvc.perform(get("/" + GameMappings.PLAY))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.GAME_OVER));
    }

    @Test
    public void processMessage() throws Exception {
        mockMvc.perform(post("/" + GameMappings.CHANGE).param("direction", "Q"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
    }

    @Test
    public void addItemToInventory() throws Exception {
        mockMvc.perform(post("/" + GameMappings.ADD).param("item", "GOLD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
    }

    @Test
    public void home() throws Exception {
        mockMvc.perform(get(GameMappings.HOME))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.HOME));
    }

    @Test
    public void restart() throws Exception {
        mockMvc.perform(get("/" + GameMappings.RESTART))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
    }

    @Test
    public void exit() throws Exception {
        mockMvc.perform(get("/" + GameMappings.EXIT))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.GAME_OVER));
    }

//    @Test
//    public void changeDirectionNotFound() throws Exception {
//
//        when(gameService.changeDirection(anyString())).thenThrow(NotFoundException.class);
//
//        mockMvc.perform(post("/" + GameMappings.CHANGE).param("direction", "Q"))
//                .andExpect(status().isNotFound())
//                .andExpect(view().name("404error"));
//    }

}