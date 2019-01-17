package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.LocationId;
import com.tkjavadev.adventuregame.core.LocationIdImpl;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Location;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    private GameServiceImpl gameService;

    @Mock
    LocationId locationId;

    @Mock
    LocationService locationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationId=new LocationIdImpl(1L);
        gameService = new GameServiceImpl(locationId,locationService);
    }

    @Test
    public void isGameOver() {
        assertFalse(gameService.isGameOver());

        locationId.setLocationId(141L);
        assertTrue(gameService.isGameOver());

        locationId.setLocationId(-141L);
        assertFalse(gameService.isGameOver());
    }

    @Test
    public void reset() {
        locationId.setLocationId(5L);
        gameService.reset();
        assertEquals(Long.valueOf(1L), locationId.getLocationId());
    }

    @Test
    public void changeDirection() {
        Location location=new Location();
        Gate exit=new Gate();
        exit.setDirection("E");
        exit.setDestId(99L);
        List<Gate> exits=new ArrayList<>();
        exits.add(exit);
        location.setGates(exits);

        when(locationService.getLocationById(anyLong())).thenReturn(location);

        assertEquals(Long.valueOf(99L),gameService.changeDirection(exit.getDirection()));
        verify(locationService,times(1)).getLocationById(anyLong());
    }

    @Test
    public void getAvaliableGates() {
        Location location=new Location();
        List<Gate> exits=new ArrayList<>();
        location.setGates(exits);

        when(locationService.getLocationById(anyLong())).thenReturn(location);

        assertEquals(exits,gameService.getAvaliableGates());
        verify(locationService,times(1)).getLocationById(anyLong());
    }

    @Test
    public void getDescription() {
        Location location=new Location();
        location.setDescription("Description");

        when(locationService.getLocationById(anyLong())).thenReturn(location);

        assertEquals("Description",gameService.getDescription());
        verify(locationService,times(1)).getLocationById(anyLong());
    }
}