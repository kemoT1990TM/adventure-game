package com.tkjavadev.adventuregame.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GateTestParameterized {

    private Gate gate;
    private String input;
    private String output;

    public GateTestParameterized(String input,String output) {
        this.input = input;
        this.output = output;
    }

    @Before
    public void setUp() {
        gate =new Gate();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {"N", "NORTH"},
                {"S", "SOUTH"},
                {"W", "WEST"},
                {"E", "EAST"},
                {"B", "BACK"},
                {"Q", "QUIT"},
                {"U", "UP"},
                {"D", "DOWN"},
                {"NE", "NORTH EAST"},
                {"NW", "NORTH WEST"},
                {"SE", "SOUTH EAST"},
                {"SW", "SOUTH WEST"},
                {"X", "XYZZY"},
                {"O", "OUT"},
                {"ENT", "ENTER"},
                {"P", "PLUGH"},
                {"DB", "DEBRIS"},
                {"DE", "DEPRESSION"},
                {"C", "CROSS"},
                {"SEC", "SECRET"},
                {"Y2", "Y2"},
                {"SL", "SLAB"},
                {"BU", "BUILDING"}
        });
    }

    @Test
    public void getFullName() {
        gate.setDirection(input);
        assertEquals(output, gate.getFullName());
    }
}