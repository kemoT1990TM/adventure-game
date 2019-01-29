package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.core.InitVariablesImpl;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
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
    InitVariables initVariables;

    @Mock
    LocationService locationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        initVariables =new InitVariablesImpl(1L);
        gameService = new GameServiceImpl(initVariables,locationService);
    }

    @Test
    public void isGameOver() {
        assertFalse(gameService.isGameOver());

        initVariables.setLocationId(80L);
        assertTrue(gameService.isGameOver());

        initVariables.setLocationId(-80L);
        assertFalse(gameService.isGameOver());

        initVariables.setLocationId(10L);
        initVariables.addToInventory("GOLD");
        initVariables.addToInventory("DIAMONDS");
        initVariables.addToInventory("COINS");
        initVariables.addToInventory("SILVER");
        assertFalse(gameService.isGameOver());
        initVariables.addToInventory("JEWELRY");
        assertTrue(gameService.isGameOver());
    }

    @Test
    public void reset() {
        initVariables.setLocationId(5L);
        gameService.reset();
        assertEquals(Long.valueOf(1L), initVariables.getLocationId());
    }

//    @Test
//    public void changeDirection() {
//        Location location=new Location();
//        Gate exit=new Gate();
//        exit.setDirection("E");
//        exit.setDestId(99L);
//        List<Gate> exits=new ArrayList<>();
//        exits.add(exit);
//        location.setGates(exits);
//
//        when(locationService.getLocationById(anyLong())).thenReturn(location);
//
//        assertEquals(Long.valueOf(99L),gameService.changeDirection(exit.getDirection()));
//        verify(locationService,times(1)).getLocationById(anyLong());
//    }

    @Test
    public void getAvailableGates() {
        Location location=new Location();
        List<Gate> exits=new ArrayList<>();
        location.setGates(exits);

        when(locationService.getLocationById(anyLong())).thenReturn(location);

        assertEquals(exits,gameService.getAvailableGates());
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

    @Test
    public void getAvailableItems() {
        Location location=new Location();
        List<Item> items=new ArrayList<>();
        location.setItems(items);

        when(locationService.getLocationById(anyLong())).thenReturn(location);

        assertEquals(items,gameService.getAvailableItems());
        verify(locationService,times(1)).getLocationById(anyLong());
    }

    @Test
    public void addItemToInventory() {
        Item item1=new Item();
        item1.setName("item1");
        item1.setRequired("NOT");

        when(locationService.getItemByLocIdAndName(anyLong(),anyString())).thenReturn(item1);


        assertNull(gameService.getItemMessage());
        gameService.addItemToInventory("item1");
        assertNotNull(gameService.getItemMessage());
        assertNull(gameService.getGateMessage());
        assertEquals("item1",gameService.printInventory());
        assertEquals(item1.getName() + " -> ADDED TO INVENTORY",gameService.getItemMessage());
        gameService.addItemToInventory("item1");
        assertEquals("item1",gameService.printInventory());
        assertEquals(item1.getName() + " -> ALREADY IN INVENTORY",gameService.getItemMessage());


        Item item2=new Item();
        item2.setName("item2");
        item2.setRequired("item3");
        when(locationService.getItemByLocIdAndName(anyLong(),anyString())).thenReturn(item2);
        gameService.addItemToInventory("item2");
        assertEquals("YOU NEED " + item2.getRequired() + " TO GET " + item2.getName(),gameService.getItemMessage());

        item2.setRequired("item1");
        gameService.addItemToInventory("item2");
        assertEquals(item2.getName() + " -> ADDED TO INVENTORY",gameService.getItemMessage());
    }

}