package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class GameImplTest {

    GameImpl game;

    @Mock
    LocationService locationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        game = new GameImpl(locationService);
    }

    @Test
    public void isGameOver() {
        game.setLocationID(5L);
        assertEquals(false, game.isGameOver());

        game.setLocationID(141L);
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void reset() {
        game.setLocationID(5L);
        game.reset();
        assertEquals(Long.valueOf(1L), game.getLocationID());
    }
}