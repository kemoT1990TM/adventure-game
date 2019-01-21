package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;
import com.tkjavadev.adventuregame.domain.Location;
import com.tkjavadev.adventuregame.repositories.ItemRepository;
import com.tkjavadev.adventuregame.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final Logger log= LoggerFactory.getLogger(ItemServiceImpl.class);

    // == fields ==
    private ItemRepository itemRepository;
    private LocationRepository locationRepository;

    public ItemServiceImpl(ItemRepository itemRepository, LocationRepository locationRepository) {
        this.itemRepository = itemRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Item getItemByLocIdAndName(Long id,String name) {

        Optional<Location> locationOptional = locationRepository.findById(id);

        if (!locationOptional.isPresent()){
            //todo impl error handling
            log.error("Location id not found. Id: " + id);
        }

        Location location = locationOptional.get();
        Optional<Item> itemOptional = location.getItems().stream()
                .filter(item -> item.getName().equals(name)).findFirst();
        if(!itemOptional.isPresent()){
            //todo impl error handling
            log.error("Item name not found: " + name);
        }
        return itemOptional.get();
    }
}