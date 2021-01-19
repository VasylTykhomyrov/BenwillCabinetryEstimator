package com.benwillcabinets.benwillestimator.domain;

import com.benwillcabinets.benwillestimator.refacing.RefacingInfo;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.scenario.effect.impl.prism.PrImage;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> listOfTransactions = new ArrayList<>();
    private String address;
    private String contactInfo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public static String getCurrentTimestamp() {
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

    @JsonProperty("listOfServices")
    public List<ProjectItem> getListOfServices() {
        List<Category> services = Category.getServiceCategories();
        Comparator<? super ProjectItem> byDate = new Comparator<ProjectItem>() {
            @Override
            public int compare(ProjectItem p1, ProjectItem p2) {
                if (p1.getScheduledFor() == null) {
                    return 1;
                }
                if (p2.getScheduledFor() == null) {
                    return -1;
                }
                return p1.getScheduledFor().compareTo(p2.getScheduledFor());
            }
        };
        return listOfProducts.stream()
                .filter(projectItem -> services.contains(projectItem.getProduct().getCategory()))
                .sorted(byDate)
                .collect(Collectors.toList());
    }

    public List<Transaction> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<Transaction> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
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
