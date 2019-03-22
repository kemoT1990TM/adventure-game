package com.tkjavadev.adventuregame.core;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private String item;
    private List<String> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
}
