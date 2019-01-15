package com.tkjavadev.adventuregame.domain;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExitTest {

    Exit exit;

    @Before
    public void setUp() {
        exit=new Exit();
    }

    @Test
    public void getFullName() {
        assertNotNull(exit.getFullName());
        assertEquals("BAD EXIT",exit.getFullName());
        exit.setDirection("N");
        assertEquals("NORTH",exit.getFullName());
    }
}