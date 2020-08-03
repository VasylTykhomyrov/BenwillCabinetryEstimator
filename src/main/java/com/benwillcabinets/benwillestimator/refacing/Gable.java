package com.benwillcabinets.benwillestimator.refacing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gable {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private double gableCost;
    private double gableSell;
    private double width;
    private double height;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getGableCost() {
        return gableCost;
    }

    public void setGableCost(double gableCost) {
        this.gableCost = gableCost;
    }

    public double getGableSell() {
        return gableSell;
    }

    public void setGableSell(double gableSell) {
        this.gableSell = gableSell;
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

    private String special_notes;
}
