package com.tkjavadev.adventuregame.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    // == fields ==
    @Id
    private String id;
    private Long locId;
    private String description;

    private List<Gate> gates = new ArrayList<>();

    private List<Item> items = new ArrayList<>();

    // == methods ==
    public String getId() {
        return id;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
