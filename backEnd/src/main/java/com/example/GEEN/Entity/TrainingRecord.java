package com.example.GEEN.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_record")
public class TrainingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String accountName;
    public String category;
    public Double capacity;
    @CreationTimestamp
    @Column(name = "date")
    public Date date;

    public TrainingRecord(){

    }

    public TrainingRecord(String accountName,String category,Double capacity) {
        this.accountName=accountName;
        this.capacity=capacity;
        this.category=category;
    }

    public Date getDate() {
        return date;
    }

    public Double getCapacity() {
        return capacity;
    }

    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCategory() {
        return category;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
