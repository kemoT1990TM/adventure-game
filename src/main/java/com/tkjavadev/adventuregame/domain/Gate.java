package com.tkjavadev.adventuregame.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gate {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Long locId;
    private String direction;
    private Long destId;
    private String required;

    // == methods ==
    /*
    Returns full name of direction as a String
     */
    public String getFullName() {
        String fullName = null;
        if (direction != null && !direction.isEmpty()) {
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
                case "X":
                    fullName = "XYZZY";
                    break;
                case "O":
                    fullName = "OUT";
                    break;
                case "ENT":
                    fullName = "ENTER";
                    break;
                case "DE":
                    fullName = "DEPRESSION";
                    break;
                case "P":
                    fullName = "PLUGH";
                    break;
                case "BU":
                    fullName = "BUILDING";
                    break;
                case "DB":
                    fullName = "DEBRIS";
                    break;
                case "C":
                    fullName = "CROSS";
                    break;
                case "SEC":
                    fullName = "SECRET";
                    break;
                case "B":
                    fullName = "BACK";
                    break;
                case "SL":
                    fullName = "SLAB";
                    break;
                case "Y2":
                    fullName = "Y2";
                    break;
            }
        } else {
            fullName = "BAD EXIT";
        }
        return fullName;
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

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
