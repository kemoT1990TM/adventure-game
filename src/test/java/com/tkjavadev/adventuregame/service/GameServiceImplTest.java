package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.core.GameImpl;
import com.tkjavadev.adventuregame.core.MessageGeneratorImpl;
import com.tkjavadev.adventuregame.domain.Exit;
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

    @Mock
    MessageGeneratorImpl messageGenerator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        gameService = new GameServiceImpl(game, messageGenerator);
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
        List<Exit> exits=new ArrayList<>();
        location.setExits(exits);

        when(gameService.getAvaliableExits()).thenReturn(exits);
    }

//    @Test
//    public void testChangeDirection() {
//        Location location=new Location();
//        location.setId(2L);
//        Exit exit=new Exit();
//        exit.setLocId(2L);
//        exit.setDirection("W");
//        exit.setDestId(99L);
//        List<Exit> exits=new ArrayList<>();
//        exits.add(exit);
//        location.setExits(exits);
//        assertEquals(Long.valueOf(99L),gameService.changeDirection("W"));
//    }
}