package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
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

        when(locationRepository.findById(anyLong())).thenReturn(locationOptional);

        Location locationReturned=locationRepository.findById(1L).get();

        assertNotNull("Null location returned",locationReturned);
        verify(locationRepository,times(1)).findById(anyLong());
        verify(locationRepository,never()).findAll();
    }

    @Test
    public void saveLocation() {
        Location location=new Location();

        locationService.saveLocation(location);
        verify(locationRepository,times(1)).save(location);
    }

}