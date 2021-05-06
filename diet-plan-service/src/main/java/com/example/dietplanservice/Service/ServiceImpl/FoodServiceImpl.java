package com.example.dietplanservice.Service.ServiceImpl;

import com.example.dietplanservice.Entity.Food;
import com.example.dietplanservice.Repository.FoodRepository;
import com.example.dietplanservice.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nyx
 * @title: FoodServiceImpl
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2316:04
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> findFoodByName(String foodName) {
        return foodRepository.findFoodByName(foodName);
    }

    @Override
    public List<Food> findAllFood() {
        return foodRepository.findAll();
    }
}
