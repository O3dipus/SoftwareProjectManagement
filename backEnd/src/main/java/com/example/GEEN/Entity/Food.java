package com.example.GEEN.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nyx
 * @title: Food
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:05
 */
@Entity
@Table(name = "food")
public class Food {
    @Id
    public Long id;
    public String name;
    public String measurement;
    public Double carbohydrate;
    public Double fat;
    public Double protein;
    public String vitamin;
    public Food(){

    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getFat() {
        return fat;
    }

    public String getVitamin() {
        return vitamin;
    }
}
