package com.tkjavadev.adventuregame.controllers;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.services.GameService;
import com.tkjavadev.adventuregame.util.GameMappings;
import com.tkjavadev.adventuregame.util.ViewNames;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class GameControllerTest {

    @Mock
    GameService gameService;

    GameController gameController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        gameController = new GameController(gameService);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController)
                .setViewResolvers(viewResolver).build();

        // mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void play() throws Exception {
        mockMvc.perform(get("/" + GameMappings.RESTART))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/" + ViewNames.PLAY));
    }

    @Test
    public void processMessage() throws Exception {
        Location location=new Location();
        location.setDescription("Description");
        List<Gate> gates =new ArrayList<>();
        location.setGates(gates);

        when(gameService.getDescription()).thenReturn(location.getDescription());
        when(gameService.getAvaliableGates()).thenReturn(gates);

        mockMvc.perform(post("/" + GameMappings.CHANGE).param("direction", "Q"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(GameMappings.REDIRECT_PLAY));

        mockMvc.perform(get("/" + GameMappings.PLAY))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.PLAY));
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
}