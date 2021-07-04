package com.example.GEEN.Controller;

import com.example.GEEN.Entity.Food;
import com.example.GEEN.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nyx
 * @title: FoodController
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2317:05
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("/food")
    public List<Food> getFood(@RequestParam(value = "name",required = false) String foodName) {
        if(foodName==null) {
            return foodService.findAllFood();
        }
        else {
            List<Food> food = foodService.findFoodByName(foodName);
            return food;
        }

    }
}
