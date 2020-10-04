package com.benwillcabinets.benwillestimator.domain;

import com.benwillcabinets.benwillestimator.refacing.RefacingInfo;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import com.sun.scenario.effect.impl.prism.PrImage;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class ProjectEstimate {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectItem> listOfProducts = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private RefacingInfo refacingInfo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefacingItem> listOfRefacingItems = new ArrayList<>();
    private String address;
    private String contactInfo;

    public static String getDateCreated() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RefacingInfo getRefacingInfo() {
        return refacingInfo;
    }

    public void setRefacingInfo(RefacingInfo refacingInfo) {
        this.refacingInfo = refacingInfo;
    }

    public List<ProjectItem> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProjectItem> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public List<RefacingItem> getListOfRefacingItems() {
        return listOfRefacingItems;
    }

    public void setListOfRefacingItems(List<RefacingItem> listOfRefacingItems) {
        this.listOfRefacingItems = listOfRefacingItems;
    }

    public String getAddress() {
        return address;
    }

    public String getTotal() {
        List<ProjectItem> items = getListOfProducts();
        double totalSellPrice=0.0;
        for (ProjectItem item : items) {
            totalSellPrice += (item.getSellProjectPrice().doubleValue() * item.getQty());
        }
        return Double.toString(totalSellPrice);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
