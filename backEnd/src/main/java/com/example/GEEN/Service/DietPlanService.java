package com.example.GEEN.Service;

import com.example.GEEN.Entity.DietPlan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: DietPlanService
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:21
 */
public interface DietPlanService {
    public Optional<DietPlan> findPlanById(Long id);
    public List<DietPlan> findPlanByNameAndTime(String accountName, Date date);
    public List<DietPlan> findPlanByName(String accountName);
    public void deletePlanById(Long id);
    public void changePlan(DietPlan plan);
    public List<DietPlan> findByTime(Date date);
}
