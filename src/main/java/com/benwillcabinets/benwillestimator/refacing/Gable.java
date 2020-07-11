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
    private String width;
    private String height;
    private String special_notes;
}
