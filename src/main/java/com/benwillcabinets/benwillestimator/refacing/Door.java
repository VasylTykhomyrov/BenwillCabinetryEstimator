package com.benwillcabinets.benwillestimator.refacing;

import com.benwillcabinets.benwillestimator.domain.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity   // annotation to add DB
public class Door {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private double doorCost;
    private double doorSell;
    private double width;
    private double height;
    private String special_notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDoorCost() {
        return doorCost;
    }

    public void setDoorCost(double doorCost) {
        this.doorCost = doorCost;
    }

    public double getDoorSell() {
        return doorSell;
    }

    public void setDoorSell(double doorSell) {
        this.doorSell = doorSell;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSpecial_notes() {
        return special_notes;
    }

    public void setSpecial_notes(String special_notes) {
        this.special_notes = special_notes;
    }
}
