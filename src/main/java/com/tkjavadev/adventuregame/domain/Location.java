package com.tkjavadev.adventuregame.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Lob
    private String description;

    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locId", fetch = FetchType.LAZY)
    private List<Gate> gates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locId")
    private List<Item> items = new ArrayList<>();

    // == methods ==
    public String getId() {
        return id;
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
