package com.tkjavadev.adventuregame.bootstrap;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.GateRepository;
import com.tkjavadev.adventuregame.repositories.ItemRepository;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@Component
public class AdventureBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final GateRepository gateRepository;
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    public AdventureBootStrap(GateRepository gateRepository, LocationRepository locationRepository,
                              ItemRepository itemRepository) {
        this.gateRepository = gateRepository;
        this.locationRepository = locationRepository;
        this.itemRepository=itemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//            readData();
    }

    private void readData() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(
                    "src\\main\\resources\\locations_79.txt"));
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
                    "src\\main\\resources\\directions_79.txt")));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] data = input.split(",");
                long loc = Integer.parseInt(data[0]);
                String direction = data[1];
                long destination = Integer.parseInt(data[2]);
                String required = data[3];
                System.out.println(loc + ": " + direction + ": " + destination+": "+required);
                List<Gate> gates = locationRepository.findById(loc).get().getGates();
                ;
                List<String> directions = new ArrayList<>();
                for (Gate ex : gates) {
                    directions.add(ex.getDirection());
                }
                if (!directions.contains("Q")) {
                    Gate gateQuit = new Gate();
                    gateQuit.setLocId(loc);
                    gateQuit.setDirection("Q");
                    gateQuit.setDestId(80L);
                    gateQuit.setRequired("NOT");
                    gateRepository.save(gateQuit);
                }
                Gate gate = new Gate();
                gate.setLocId(loc);
                gate.setDirection(direction);
                gate.setDestId(destination);
                gate.setRequired(required);
                gateRepository.save(gate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        try {
            scanner = new Scanner(new FileReader(
                    "src\\main\\resources\\items_79.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] data = input.split(",");
                long loc = Integer.parseInt(data[0]);
                String name = data[1];
                String description=data[2];
                String req=data[3];
                System.out.println("Imported item: "+name+ ": " + description+": "+req);
                Item item=new Item();
                item.setLocId(loc);
                item.setName(name);
                item.setDescription(description);
                item.setRequired(req);
                itemRepository.save(item);
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
