package com.example.GEEN.Entity;

/**
 * @author nyx
 * @title: daily diet plan
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2314:49
 */

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diet_plan")
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "account_name")
    public String accountName;

    @Column(name = "food_name")
    public String foodName;
    @Column(name = "amount")
    public double amount;

    @CreationTimestamp
    @Column(name = "date")
    public Date date;

    public DietPlan(){

    }
    public DietPlan(String accountName,String foodName,double amount) {
        this.accountName=accountName;
        this.foodName=foodName;
        this.amount=amount;
    }
    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
