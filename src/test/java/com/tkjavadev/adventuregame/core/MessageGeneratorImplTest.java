package com.tkjavadev.adventuregame.core;

import com.tkjavadev.adventuregame.domain.Location;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class MessageGeneratorImplTest {

    MessageGeneratorImpl messageGenerator;

    @Mock
    Game game;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        messageGenerator = new MessageGeneratorImpl(game);
    }

    @Test
    public void getDescription() {
        Location location=new Location();
        location.setDescription("Description");

        when(messageGenerator.getDescription()).thenReturn("Description");
    }
}