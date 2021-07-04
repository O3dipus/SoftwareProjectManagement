package com.example.GEEN.Controller;

import com.example.GEEN.Entity.DietPlan;
import com.example.GEEN.Entity.Food;
import com.example.GEEN.Entity.Nutrition;
import com.example.GEEN.Service.DietPlanService;
import com.example.GEEN.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: DietPlanController
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:35
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class DietPlanController {
    @Autowired
    private DietPlanService dietPlanService;
    @Autowired
    private FoodService foodService;
    @RequestMapping("/dietPlan/nutrition")
    public Nutrition getNutrition(@RequestParam(name="time") String time,
                                  @RequestParam(value="accountName") String accountName) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<DietPlan> dietPlan = dietPlanService.findPlanByNameAndTime(accountName,date);
        Double carbohydrate = 0.0;
        Double fat = 0.0;
        Double protein = 0.0;
        String vitamin = "";

        for(int i=0;i<dietPlan.size();i++) {
            Double amount = dietPlan.get(i).amount;
            String foodName = dietPlan.get(i).foodName;
            Food food = foodService.findFoodByName(foodName).get(0);
            carbohydrate+=food.carbohydrate;
            fat+=food.fat;
            protein+=food.protein;

            if(food.vitamin!=null) {
                vitamin+=food.vitamin+"&";

            }
        }
        //System.out.println(vitamin);
        String[] vitamins = vitamin.split("\\&");
        HashMap<String,Integer> map = new HashMap<>();
        for (int i=0;i<vitamins.length;i++) {

            if (!map.containsKey(vitamins[i])) {
                map.put(vitamins[i],1);
            }
        }
        vitamin="";
        for(String key : map.keySet()){
            vitamin+=key+"&";
        }
        Nutrition nutrition = new Nutrition(carbohydrate,fat,protein,vitamin);

        return nutrition;
    }

    @RequestMapping("/dietPlan")
    public List<DietPlan> findDietPlan(@RequestParam(name = "accountName",required = true) String accountName,
                                       @RequestParam(name = "time",required = false) String time) {
        if(time==null) {
            return dietPlanService.findPlanByName(accountName);
        }
        else{
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return dietPlanService.findPlanByNameAndTime(accountName, date);
        }
    }

    @RequestMapping("/changeDietPlan")
    public String   changePlan(@RequestParam(value = "id",required = true) Long id,
                           @RequestParam(value = "foodName",required = true) String foodName,
                           @RequestParam(value = "amount",required = true) double amount) {
        Optional<DietPlan> plan = dietPlanService.findPlanById(id);
        if(plan!=null) {
            plan.get().setFoodName(foodName);
            plan.get().setAmount(amount);
            dietPlanService.changePlan(plan.get());
            return "update successfully!";
        }
        else {
            return "invalid id!";
        }
    }

    @RequestMapping("/createDietPlan")
    public String createPlan(@RequestParam(value = "accountName",required = true) String accountName,
                           @RequestParam(value = "foodName",required = true) String foodName,
                           @RequestParam(value = "amount",required = true) double amount) {

        DietPlan plan = new DietPlan(accountName,foodName,amount);
        dietPlanService.changePlan(plan);
        return "create successfully!";
    }

    @RequestMapping("/deleteDietPlan/{id}")
    public String deletePlan(@PathVariable("id") Long id) {
        Optional<DietPlan> plan = dietPlanService.findPlanById(id);
        if(plan!=null) {
            dietPlanService.deletePlanById(id);
            return "delete successfully!";
        }
        else {
            return "invalid id!";
        }
    }



}
