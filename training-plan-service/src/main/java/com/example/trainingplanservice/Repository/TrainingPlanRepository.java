package com.example.trainingplanservice.Repository;

import com.example.trainingplanservice.Entity.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author nyx
 * @title: TrainingPlanRepository
 * @projectName training-plan-service
 * @description: TODO
 * @date 2021/5/113:31
 */

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan,Long> {
    public List<TrainingPlan> findTrainingPlanByAccountNameAndDate(String accountName, Date date);
    public List<TrainingPlan> findTrainingPlanByAccountName(String accountName);
}
