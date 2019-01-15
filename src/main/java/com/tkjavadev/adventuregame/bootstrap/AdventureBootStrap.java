package com.tkjavadev.adventuregame.bootstrap;

import com.tkjavadev.adventuregame.domain.Exit;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repository.ExitRepository;
import com.tkjavadev.adventuregame.repository.LocationRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class AdventureBootStrap implements ApplicationListener<ContextRefreshedEvent> {

        private final ExitRepository exitRepository;
        private final LocationRepository locationRepository;

    public AdventureBootStrap(ExitRepository exitRepository, LocationRepository locationRepository) {
        this.exitRepository = exitRepository;
        this.locationRepository = locationRepository;
    }

    @Override
        public void onApplicationEvent(ContextRefreshedEvent arg0) {
            readData();
        }

        private void readData() {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new FileReader(
                        "src\\main\\resources\\locations_big.txt"));
                scanner.useDelimiter(",");
                while (scanner.hasNextLine()) {
                    long loc = scanner.nextInt();
                    scanner.skip(scanner.delimiter());
                    String description = scanner.nextLine();
                    System.out.println("Imported loc: " + loc + ": " + description);
                    Location location = new Location();
                    location.setDescription(description);
                    locationRepository.save(location);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }

            try {
                scanner = new Scanner(new BufferedReader(new FileReader(
                        "src\\main\\resources\\directions_big.txt")));
                scanner.useDelimiter(",");
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    String[] data = input.split(",");
                    long loc = Integer.parseInt(data[0]);
                    String direction = data[1];
                    long destination = Integer.parseInt(data[2]);
                    System.out.println(loc + ": " + direction + ": " + destination);
                    List<Exit> exits = locationRepository.findById(loc).get().getExits();;
                    List<String> directions = new ArrayList<>();
                    for (Exit ex : exits) {
                        directions.add(ex.getDirection());
                    }
                    if (!directions.contains("Q")) {
                        Exit exitQuit = new Exit();
                        exitQuit.setLocId(loc);
                        exitQuit.setDirection("Q");
                        exitQuit.setDestId(141L);
                        exitRepository.save(exitQuit);
                    }
                    Exit exit = new Exit();
                    exit.setLocId(loc);
                    exit.setDirection(direction);
                    exit.setDestId(destination);
                    exitRepository.save(exit);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
            System.out.println("Files read and data has been saved into db!");
        }
}
