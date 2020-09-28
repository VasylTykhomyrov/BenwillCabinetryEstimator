package com.benwillcabinets.benwillestimator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ProjectItem {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private BigDecimal costProjectPrice;
    private BigDecimal sellProjectPrice;
    @ManyToOne(targetEntity=Product.class)
    private Product product;
    private double qty;


    public Integer getId() {
        return id;
    }

    public BigDecimal getCostProjectPrice() {
        return costProjectPrice;
    }

    public void setCostProjectPrice(BigDecimal costProjectPrice) {
        this.costProjectPrice = costProjectPrice;
    }

    public BigDecimal getSellProjectPrice() {
        return sellProjectPrice;
    }

    public void setSellProjectPrice(BigDecimal sellProjectPrice) {
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
