package com.example.GEEN.Service;

import com.example.GEEN.Entity.Food;

import java.util.List;

/**
 * @author nyx
 * @title: FoodService
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2316:04
 */
public interface FoodService {
    public List<Food> findFoodByName(String foodName);
    public List<Food> findAllFood();
}
