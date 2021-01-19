package com.benwillcabinets.benwillestimator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

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
    private boolean paid = false;
    private boolean completed = false;
    private String paidBy;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date scheduledFor;
    private int duration;
    private String assignedTo;

    public Integer getId() {
        return id;
    }

    public Date getScheduledFor() {
        return scheduledFor;
    }

    @JsonIgnore
    public String getScheduledForFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.CANADA);
        return scheduledFor == null ? "" : format.format(scheduledFor);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
