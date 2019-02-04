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
import reactor.core.publisher.Mono;

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
        initVariables = new InitVariablesImpl(1L);
        gameService = new GameServiceImpl(initVariables, locationService);
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

    @Test
    public void changeDirection() {
        Location location=new Location();
        location.setLocId(1L);
        Gate gate=new Gate();
        gate.setLocId(1L);
        gate.setDirection("E");
        gate.setDestId(99L);
        gate.setRequired("NOT");
        List<Gate> gates=new ArrayList<>();
        gates.add(gate);
        location.setGates(gates);

        when(locationService.getLocationByLocId(anyLong())).thenReturn(Mono.just(location));

        assertEquals(Long.valueOf(99L),gameService.changeDirection(gate.getDirection()));
        verify(locationService,times(1)).getLocationByLocId(anyLong());

        initVariables.setLocationId(1L);
        gate.setRequired("KEYS");
        assertEquals(Long.valueOf(1L),gameService.changeDirection(gate.getDirection()));
        assertEquals("YOU NEED " + gate.getRequired() + " TO GO THERE", gameService.getGateMessage());

        gate.setDestId(302L);
        assertEquals(Long.valueOf(25L),gameService.changeDirection(gate.getDirection()));
    }

    @Test
    public void getAvailableGates() {
        Location location = new Location();
        List<Gate> exits = new ArrayList<>();
        location.setGates(exits);

        when(locationService.getLocationByLocId(anyLong())).thenReturn(Mono.just(location));

        assertEquals(exits, gameService.getAvailableGates());
        verify(locationService, times(1)).getLocationByLocId(anyLong());
    }

    @Test
    public void getDescription() {
        Location location = new Location();
        location.setDescription("Description");

        when(locationService.getLocationByLocId(anyLong())).thenReturn(Mono.just(location));

        assertEquals("Description", gameService.getDescription());
        verify(locationService, times(1)).getLocationByLocId(anyLong());
    }

    @Test
    public void getAvailableItems() {
        Location location = new Location();
        List<Item> items = new ArrayList<>();
        location.setItems(items);

        when(locationService.getLocationByLocId(anyLong())).thenReturn(Mono.just(location));

        assertEquals(items, gameService.getAvailableItems());
        verify(locationService, times(1)).getLocationByLocId(anyLong());
    }

    @Test
    public void addItemToInventory() {
        Item item1 = new Item();
        item1.setName("item1");
        item1.setRequired("NOT");

        when(locationService.getItemByLocIdAndName(anyLong(), anyString())).thenReturn(Mono.just(item1));


        assertNull(gameService.getItemMessage());
        gameService.addItemToInventory("item1");
        assertNotNull(gameService.getItemMessage());
        assertNull(gameService.getGateMessage());
        assertEquals("item1", gameService.printInventory());
        assertEquals(item1.getName() + " -> ADDED TO INVENTORY", gameService.getItemMessage());
        gameService.addItemToInventory("item1");
        assertEquals("item1", gameService.printInventory());
        assertEquals(item1.getName() + " -> ALREADY IN INVENTORY", gameService.getItemMessage());


        Item item2 = new Item();
        item2.setName("item2");
        item2.setRequired("item3");
        when(locationService.getItemByLocIdAndName(anyLong(), anyString())).thenReturn(Mono.just(item2));
        gameService.addItemToInventory("item2");
        assertEquals("YOU NEED " + item2.getRequired() + " TO GET " + item2.getName(), gameService.getItemMessage());

        item2.setRequired("item1");
        gameService.addItemToInventory("item2");
        assertEquals(item2.getName() + " -> ADDED TO INVENTORY", gameService.getItemMessage());
    }

    @Test
    public void getVisitedLocations() {
        initVariables.addVisitedLocation(1L);
        initVariables.addVisitedLocation(2L);
        assertEquals(Integer.valueOf(1), gameService.getVisitedLocations());
    }

    @Test
    public void resetMessage() {
        gameService.resetMessages();
        assertNull(gameService.getItemMessage());
    }

    @Test
    public void exit() {
        initVariables.setLocationId(5L);
        gameService.exit();
        assertEquals(Long.valueOf(80L), initVariables.getLocationId());
    }

    @Test
    public void getScore() {
        initVariables.addVisitedLocation(1L);
        assertEquals(Integer.valueOf(0), gameService.getScore());

        initVariables.addToInventory("FOOD");
        assertEquals(Integer.valueOf(5), gameService.getScore());
        initVariables.addToInventory("BOOTLE");
        assertEquals(Integer.valueOf(10), gameService.getScore());
        initVariables.addToInventory("GOLD");
        assertEquals(Integer.valueOf(42), gameService.getScore());
        initVariables.addToInventory("JEWELERY");
        assertEquals(Integer.valueOf(82), gameService.getScore());
        initVariables.addToInventory("SILVER");
        assertEquals(Integer.valueOf(110), gameService.getScore());
        initVariables.addToInventory("DIAMONDS");
        assertEquals(Integer.valueOf(174), gameService.getScore());
        initVariables.addToInventory("COINS");
        assertEquals(Integer.valueOf(194), gameService.getScore());
    }

    @Test
    public void getRank() {
        String rank = "You are obviously a rank amateur.  Better luck next time.";
        assertEquals(rank, gameService.getRank());

//        when(initVariables.getScore()).thenReturn(50);
//        rank = "Your score qualifies you as a novice class adventurer.";
//        assertEquals(rank, gameService.getRank());
//        when(initVariables.getScore()).thenReturn(80);
//        rank = "You have achieved the rating \"Experienced Adventurer\".";
//        assertEquals(rank, gameService.getRank());
//        when(initVariables.getScore()).thenReturn(125);
//        rank = "You may now consider yourself a \"Seasoned Adventurer\".";
//        assertEquals(rank, gameService.getRank());
//        when(initVariables.getScore()).thenReturn(180);
//        rank = "You have reached \"Junior Master\" status.";
//        assertEquals(rank, gameService.getRank());
//        when(initVariables.getScore()).thenReturn(222);
//        rank = "Your score qualifies you as a Master Adventurer.";
//        assertEquals(rank, gameService.getRank());
//        when(initVariables.getScore()).thenReturn(1000);
//        rank = "All of Adventuredom gives tribute to you, Adventure Grandmaster!";
//        assertEquals(rank, gameService.getRank());
    }
}