package com.tkjavadev.adventuregame.bootstrap;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.reactive.GateReactiveRepository;
import com.tkjavadev.adventuregame.repositories.reactive.ItemReactiveRepository;
import com.tkjavadev.adventuregame.repositories.reactive.LocationReactiveRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class AdventureBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final GateReactiveRepository gateReactiveRepository;
    private final LocationReactiveRepository locationReactiveRepository;
    private final ItemReactiveRepository itemReactiveRepository;

    private AdventureBootStrap(GateReactiveRepository gateReactiveRepository, LocationReactiveRepository locationReactiveRepository, ItemReactiveRepository itemReactiveRepository) {
        this.gateReactiveRepository = gateReactiveRepository;
        this.locationReactiveRepository = locationReactiveRepository;
        this.itemReactiveRepository = itemReactiveRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        loadData();
    }

    /*
    Reads data from txt files and adding to Database
     */
    private void loadData() {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(
                    "src\\main\\resources\\directions_79.txt")));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] data = input.split(",");
                long loc = Long.parseLong(data[0]);
                String direction = data[1];
                long destination = Integer.parseInt(data[2]);
                String required = data[3];
                System.out.println(loc + ": " + direction + ": " + destination + ": " + required);
                Gate gate = new Gate();
                gate.setLocId(loc);
                gate.setDirection(direction);
                gate.setDestId(destination);
                gate.setRequired(required);
                gateReactiveRepository.save(gate).block();
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
                long loc = Long.parseLong(data[0]);
                String name = data[1];
                String description = data[2];
                String req = data[3];
                System.out.println("Imported item: " + name + ": " + description + ": " + req);
                Item item = new Item();
                item.setLocId(loc);
                item.setName(name);
                item.setDescription(description);
                item.setRequired(req);
                itemReactiveRepository.save(item).block();
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
                    "src\\main\\resources\\locations_79.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                long loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Location location = new Location();
                location.setLocId(loc);
                location.setDescription(description);
                location.setGates(gateReactiveRepository.findByLocId(loc).collectList().block().stream().map(Gate::getId).collect(Collectors.toList()));
                System.out.println(location.getGates());
                location.setItems(itemReactiveRepository.findByLocId(loc).collectList().block().stream().map(Item::getId).collect(Collectors.toList()));
                System.out.println(location.getItems());
                locationReactiveRepository.save(location).block();
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
