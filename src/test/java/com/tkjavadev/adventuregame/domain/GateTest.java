package com.tkjavadev.adventuregame.domain;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GateTest {

    Gate gate;

    @Before
    public void setUp() {
        gate =new Gate();
    }

    @Test
    public void getFullName() {
        assertNotNull(gate.getFullName());
        assertEquals("BAD EXIT", gate.getFullName());
        gate.setDirection("N");
        assertEquals("NORTH", gate.getFullName());
    }
}