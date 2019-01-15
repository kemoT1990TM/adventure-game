package com.tkjavadev.adventuregame.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
public class Exit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long locId;
    private String direction;
    private Long destId;

    public String getFullName() {
        String fullName =  null;
        if(direction!=null && !direction.isEmpty()){
            switch (direction) {
                case "Q":
                    fullName = "QUIT";
                    break;
                case "N":
                    fullName = "NORTH";
                    break;
                case "S":
                    fullName = "SOUTH";
                    break;
                case "E":
                    fullName = "EAST";
                    break;
                case "W":
                    fullName = "WEST";
                    break;
                case "U":
                    fullName = "UP";
                    break;
                case "D":
                    fullName = "DOWN";
                    break;
                case "NE":
                    fullName = "NORTH EAST";
                    break;
                case "NW":
                    fullName = "NORTH WEST";
                    break;
                case "SE":
                    fullName = "SOUTH EAST";
                    break;
                case "SW":
                    fullName = "SOUTH WEST";
                    break;
            }
        } else {
            fullName = "BAD EXIT";
        }
        return fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getDestId() {
        return destId;
    }

    public void setDestId(Long destId) {
        this.destId = destId;
    }
}
