package com.example.GEEN.Entity;

/**
 * @author hello
 * @title: Nutrition
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2918:55
 */
public class Nutrition {
    public Double carbohydrate;
    public Double fat;
    public Double protein;
    public String vitamin;
    public Nutrition(Double carbohydrate,Double fat,Double protein,String vitamin) {
        this.carbohydrate=carbohydrate;
        this.fat=fat;
        this.protein=protein;
        this.vitamin=vitamin;
    }
}
