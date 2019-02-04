package com.tkjavadev.adventuregame.services;

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

    LocationServiceImpl locationService;

    @Mock
    LocationReactiveRepository locationReactiveRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        locationService=new LocationServiceImpl(locationReactiveRepository);
    }

    @Test
    public void getLocationById() {
        Location location=new Location();

//        Optional<Location> locationOptional = Optional.of(location);

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

//        Optional<Location> locationOptional = Optional.of(location);
        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));

        Item itemReturned=locationService.getItemByLocIdAndName(1L,"item").block();

        assertNotNull("Null item returned",itemReturned);
        assertEquals(Long.valueOf(1L),itemReturned.getLocId());
        assertEquals("item",itemReturned.getName());
        verify(locationReactiveRepository,times(1)).findByLocId(anyLong());
    }

//    @Test(expected = NotFoundException.class)
//    public void getItemByLocIdAndNameNotFound() {
//
////        Optional<Location> locationOptional = Optional.empty();
//        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.empty());
//        locationService.getItemByLocIdAndName(1L, "item");
//    }
//
//        @Test(expected = NotFoundException.class)
//        public void getItemByLocIdAndNameItemNotFound() {
//        Location location=new Location();
////        Optional<Location> locationOptional2=Optional.of(location);
//        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.just(location));
//        locationService.getItemByLocIdAndName(1L,"item");
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void getLocationByIdNotFound() {
////        Optional<Location> locationOptional=Optional.empty();
//        when(locationReactiveRepository.findByLocId(anyLong())).thenReturn(Mono.empty());
//        locationService.getLocationByLocId(1L);
//    }

}