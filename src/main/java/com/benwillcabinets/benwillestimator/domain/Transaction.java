package com.benwillcabinets.benwillestimator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Transaction {
    @Id   //add a primary key
    @GeneratedValue
    private Integer id;
    private String date;
    private String allocatedFor;
    private Boolean received;
    private BigDecimal amount;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAllocatedFor() {
        return allocatedFor;
    }

    public void setAllocatedFor(String allocatedFor) {
        this.allocatedFor = allocatedFor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
