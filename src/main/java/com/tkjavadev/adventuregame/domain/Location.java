package com.tkjavadev.adventuregame.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;

    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "locId", insertable = false, updatable = false)
    private List<Gate> gates = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "locId", insertable = false, updatable = false)
    private List<Item> items = new ArrayList<>();

    // == methods ==
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
