package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.core.GameImpl;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Location;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GameServiceImplTest {

    GameServiceImpl gameService;

    @Mock
    GameImpl game;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        gameService = new GameServiceImpl(game);
    }

    @Test
    public void getDescription() {
        Location location=new Location();
        location.setDescription("Description");

        when(gameService.getDescription()).thenReturn("Description");
    }

    @Test
    public void getAvaliableExits() {
        Location location=new Location();
        List<Gate> gates =new ArrayList<>();
        location.setGates(gates);

        when(gameService.getAvaliableGates()).thenReturn(gates);
    }

//    @Test
//    public void testChangeDirection() {
//        Location location=new Location();
//        location.setId(2L);
//        Gate exit=new Gate();
//        exit.setLocId(2L);
//        exit.setDirection("W");
//        exit.setDestId(99L);
//        List<Gate> exits=new ArrayList<>();
//        exits.add(exit);
//        location.setGates(exits);
//        assertEquals(Long.valueOf(99L),gameService.changeDirection("W"));
//    }
}