package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.core.InitVariables;
import com.tkjavadev.adventuregame.core.InitVariablesImpl;
import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class GameServiceImplParameterizedTest {

    private GameServiceImpl gameService;
    private Long input;
    private Long output;

    @Mock
    private InitVariables initVariables;

    @Mock
    private LocationService locationService;

    public GameServiceImplParameterizedTest(Long input, Long output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        initVariables = new InitVariablesImpl();
        gameService = new GameServiceImpl(initVariables, locationService);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {301L, 23L},
                {302L, 25L},
                {303L, 20L},
                {304L, 22L},
                {305L, 80L},
                {306L, 28L},
                {307L, 29L},
                {308L, 30L},
                {309L, 80L},
                {310L, 80L},
                {311L, 9L}
        });
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

        when(locationService.getGatesByLocId(anyLong())).thenReturn(Flux.fromIterable(gates));

        initVariables.setLocationId(1L);
        gate.setRequired("KEYS");

        gate.setDestId(input);
        assertEquals(Long.valueOf(output),gameService.changeDirection(gate));
    }
}
