package com.example.GEEN.Service;

import com.example.GEEN.Entity.TrainingPlan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: TrainingPlanService
 * @projectName training-plan-service
 * @description: TODO
 * @date 2021/5/113:35
 */
public interface TrainingPlanService {
    public List<TrainingPlan> findPlanByNameAndDate(String accountName, Date date);
    public List<TrainingPlan> findPlanByName(String accountName);
    public void deletePlan(Long id);
    public void changePlan(TrainingPlan trainingPlan);
    public Optional<TrainingPlan> findPlanById(Long id);

}
