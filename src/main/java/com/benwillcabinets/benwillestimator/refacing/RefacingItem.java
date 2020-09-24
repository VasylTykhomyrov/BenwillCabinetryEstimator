package com.benwillcabinets.benwillestimator.refacing;

import com.benwillcabinets.benwillestimator.refacing.materials.Colour;
import com.benwillcabinets.benwillestimator.refacing.materials.Handles;
import com.benwillcabinets.benwillestimator.refacing.materials.Style;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RefacingItem {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private RefacingItemType type;
    private double width;
    private double height;
    private String specialNotes;

    private double costPSF;

    private double costRefacingItemPrice;
    private double sellRefacingItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public double getCostPSF() {
        return costPSF;
    }

    public void setCostPSF(double costPSF) {
        this.costPSF = costPSF;
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

    public RefacingItemType getType() {
        return type;
    }

    public void setType(RefacingItemType type) {
        this.type = type;
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
