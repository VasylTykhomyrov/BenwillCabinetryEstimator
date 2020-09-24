package com.benwillcabinets.benwillestimator.refacing;

import com.benwillcabinets.benwillestimator.refacing.materials.Colour;
import com.benwillcabinets.benwillestimator.refacing.materials.Handles;
import com.benwillcabinets.benwillestimator.refacing.materials.Style;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RefacingInfo {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;

    private Colour colour;
    private Handles handles;
    private Style style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSFPrice(){
        if((colour == Colour.WHITE)&&(style == Style.SHAKER_VGROOVE)) {
        return 7.60;
        }
        return 0;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Handles getHandles() {
        return handles;
    }

    public void setHandles(Handles handles) {
        this.handles = handles;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
