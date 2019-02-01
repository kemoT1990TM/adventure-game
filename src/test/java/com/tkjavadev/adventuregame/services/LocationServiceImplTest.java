package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.exceptions.NotFoundException;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LocationServiceImplTest {

    LocationServiceImpl locationService;

    @Mock
    LocationRepository locationRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        locationService=new LocationServiceImpl(locationRepository);
    }

    @Test
    public void getLocationById() {
        Location location=new Location();

        Optional<Location> locationOptional = Optional.of(location);

        when(locationRepository.findByLocId(anyLong())).thenReturn(locationOptional);

        Location locationReturned=locationService.getLocationByLocId(1L);

        assertNotNull("Null location returned",locationReturned);
        assertEquals(location,locationReturned);
        verify(locationRepository,times(1)).findByLocId(anyLong());
        verify(locationRepository,never()).findAll();
    }

    @Test
    public void saveLocation() {
        Location location=new Location();

        locationService.saveLocation(location);
        verify(locationRepository,times(1)).save(location);
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

        Optional<Location> locationOptional = Optional.of(location);
        when(locationRepository.findByLocId(anyLong())).thenReturn(locationOptional);

        Item itemReturned=locationService.getItemByLocIdAndName(1L,"item");

        assertNotNull("Null item returned",itemReturned);
        assertEquals(Long.valueOf(1L),itemReturned.getLocId());
        assertEquals("item",itemReturned.getName());
        verify(locationRepository,times(1)).findByLocId(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getItemByLocIdAndNameNotFound() {

        Optional<Location> locationOptional = Optional.empty();
        when(locationRepository.findByLocId(anyLong())).thenReturn(locationOptional);
        locationService.getItemByLocIdAndName(1L, "item");
    }

        @Test(expected = NotFoundException.class)
        public void getItemByLocIdAndNameItemNotFound() {
        Location location=new Location();
        Optional<Location> locationOptional2=Optional.of(location);
        when(locationRepository.findByLocId(anyLong())).thenReturn(locationOptional2);
        locationService.getItemByLocIdAndName(1L,"item");
    }

    @Test(expected = NotFoundException.class)
    public void getLocationByIdNotFound() {
        Optional<Location> locationOptional=Optional.empty();
        when(locationRepository.findByLocId(anyLong())).thenReturn(locationOptional);
        locationService.getLocationByLocId(1L);
    }

}