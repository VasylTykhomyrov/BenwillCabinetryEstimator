package com.benwillcabinets.benwillestimator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private boolean printable = true;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date scheduledFor;
    private String assignedTo;

    public Integer getId() {
        return id;
    }

    public Date getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(Date scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public BigDecimal getCostProjectPrice() {
        return costProjectPrice;
    }

    public void setCostProjectPrice(BigDecimal costProjectPrice) {
        this.costProjectPrice = costProjectPrice;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
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
