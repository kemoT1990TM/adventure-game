package com.tkjavadev.adventuregame;

import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureGameApplicationTests {

	@Autowired
	LocationRepository locationRepository;

	@Test
	public void contextLoadsAndDataImported() {
		assertEquals(80, locationRepository.count());
	}

}
