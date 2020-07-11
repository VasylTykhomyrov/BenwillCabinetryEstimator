package com.benwillcabinets.benwillestimator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectItem {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private double costProjectPrice;
    private double sellProjectPrice;
    @ManyToOne(targetEntity=Product.class)
    private Product product;
    private double qty;

    public Integer getId() {
        return id;
    }

    public double getCostProjectPrice() {
        return costProjectPrice;
    }

    public void setCostProjectPrice(double costProjectPrice) {
        this.costProjectPrice = costProjectPrice;
    }

    public double getSellProjectPrice() {
        return sellProjectPrice;
    }

    public void setSellProjectPrice(double sellProjectPrice) {
        this.sellProjectPrice = sellProjectPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
