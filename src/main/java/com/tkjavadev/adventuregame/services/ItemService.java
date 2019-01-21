package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Item;

public interface ItemService {

    Item getItemByLocIdAndName(Long id,String name);
}
