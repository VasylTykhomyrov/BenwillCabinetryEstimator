package com.benwillcabinets.benwillestimator.refacing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RefacingItem {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;

    private Door door;
    private Gable gable;
    private double costRefacingItemPrice;
    private double sellRefacingItemPrice;

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Gable getGable() {
        return gable;
    }

    public void setGable(Gable gable) {
        this.gable = gable;
    }

    public double getCostRefacingItemPrice() {
        return costRefacingItemPrice;
    }

    public void setCostRefacingItemPrice(double costRefacingItemPrice) {
        this.costRefacingItemPrice = costRefacingItemPrice;
    }

    public double getSellRefacingItemPrice() {
        return sellRefacingItemPrice;
    }

    public void setSellRefacingItemPrice(double sellRefacingItemPrice) {
        this.sellRefacingItemPrice = sellRefacingItemPrice;
    }


}
