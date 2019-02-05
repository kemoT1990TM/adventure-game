package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.reactive.LocationReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class LocationServiceImplTest {

    private LocationServiceImpl locationService;

    @Mock
    private LocationReactiveRepository locationReactiveRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        locationService=new LocationServiceImpl(locationReactiveRepository);
    }

    @Test
    public void getLocationById() {
        Location location=new Location();

        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        Location locationReturned=locationService.getLocationByLocId(1L).block();

        assertNotNull("Null location returned",locationReturned);
        assertEquals(location,locationReturned);
        verify(locationReactiveRepository,times(1)).findByLocId(anyLong());
        verify(locationReactiveRepository,never()).findAll();
    }

    @Test
    public void saveLocation() {
        Location location=new Location();

        locationService.saveLocation(location);
        verify(locationReactiveRepository,times(1)).save(location);
    }

    @Test
    public void getItemByLocIdAndName() {
        Location location=new Location();
        location.setLocId(1L);
        Item item=new Item();
        item.setName("item");
        item.setLocId(1L);
        List<Item> items=new ArrayList<>();
        items.add(item);
        location.setItems(items);

        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        Item itemReturned=locationService.getItemByLocIdAndName(1L,"item").block();

        assertNotNull("Null item returned",itemReturned);
        assertEquals(Long.valueOf(1L),itemReturned.getLocId());
        assertEquals("item",itemReturned.getName());
        verify(locationReactiveRepository,times(1)).findByLocId(anyLong());
    }

    @Test
    public void getItemsByLocId() {
        Location location=new Location();
        location.setLocId(1L);
        Item item=new Item();
        item.setName("item");
        item.setLocId(1L);
        List<Item> items=new ArrayList<>();
        items.add(item);
        location.setItems(items);

        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        List<Item> itemsReturned=locationService.getItemsByLocId(1L).collectList().block();

        assertNotNull("Null list of items returned",itemsReturned);
        assertEquals(items,itemsReturned);
        verify(locationReactiveRepository,times(1)).findByLocId(anyLong());
    }

    @Test
    public void getGatesByLocId() {
        Location location=new Location();
        location.setLocId(1L);
        Gate gate=new Gate();
        List<Gate> gates=new ArrayList<>();
        gates.add(gate);
        location.setGates(gates);

        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        List<Gate> gatesReturned=locationService.getGatesByLocId(1L).collectList().block();

        assertNotNull("Null list of gates returned",gatesReturned);
        assertEquals(gates,gatesReturned);
        verify(locationReactiveRepository,times(1)).findByLocId(anyLong());
    }

    @Test
    public void getDescriptionByLocId() {
        Location location=new Location();
        location.setLocId(1L);
        location.setDescription("Description");

        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        assertNotNull("Null description returned",locationService.getDescriptionByLocId(1L).block());
        assertEquals("Description",locationService.getDescriptionByLocId(1L).block());
        verify(locationReactiveRepository,times(2)).findByLocId(anyLong());
    }
}