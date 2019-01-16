package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class GameImplTest {

    GameImpl game;
    Long locationId=1L;

    @Mock
    LocationService locationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        game = new GameImpl(locationId,locationService);
    }

    @Test
    public void isGameOver() {
        game.setLocationId(5L);
        assertEquals(false, game.isGameOver());

        game.setLocationId(141L);
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void reset() {
        game.setLocationId(5L);
        game.reset();
        assertEquals(Long.valueOf(1L), game.getLocationId());
    }
}