package com.tkjavadev.adventuregame.bootstrap;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.GateRepository;
import com.tkjavadev.adventuregame.repositories.ItemRepository;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/*
Reads game data from txt files on the classpath and saves it into the database.
Enabled by default (in-memory H2); disabled for the "mysql" profile where data already exists.
 */
@Component
@ConditionalOnProperty(name = "game.bootstrap.enabled", havingValue = "true")
public class AdventureBootStrap implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(AdventureBootStrap.class);

    private final GateRepository gateRepository;
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    public AdventureBootStrap(GateRepository gateRepository, LocationRepository locationRepository,
                              ItemRepository itemRepository) {
        this.gateRepository = gateRepository;
        this.locationRepository = locationRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (locationRepository.count() > 0) {
            log.info("Data already present - skipping import");
            return;
        }
        readData();
    }

    private void readData() {
        // Locations are saved in file order, so generated IDs are 1..80
        for (String line : readLines("locations_79.txt")) {
            String[] data = line.split(",", 2);
            Location location = new Location();
            location.setDescription(data[1]);
            locationRepository.save(location);
        }

        for (String line : readLines("directions_79.txt")) {
            String[] data = line.split(",");
            Gate gate = new Gate();
            gate.setLocId(Long.parseLong(data[0].trim()));
            gate.setDirection(data[1].trim());
            gate.setDestId(Long.parseLong(data[2].trim()));
            gate.setRequired(data[3].trim());
            gateRepository.save(gate);
        }

        for (String line : readLines("items_79.txt")) {
            // locId,NAME,description (may contain commas),REQUIRED
            int first = line.indexOf(',');
            int second = line.indexOf(',', first + 1);
            int last = line.lastIndexOf(',');
            Item item = new Item();
            item.setLocId(Long.parseLong(line.substring(0, first).trim()));
            item.setName(line.substring(first + 1, second).trim());
            item.setDescription(line.substring(second + 1, last).trim());
            item.setRequired(line.substring(last + 1).trim());
            itemRepository.save(item);
        }

        log.info("Imported {} locations, {} gates, {} items",
                locationRepository.count(), gateRepository.count(), itemRepository.count());
    }

    private List<String> readLines(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().map(String::trim).filter(l -> !l.isEmpty()).toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read " + fileName, e);
        }
    }
}
