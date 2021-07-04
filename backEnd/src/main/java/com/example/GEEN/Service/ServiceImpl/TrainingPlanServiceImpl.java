package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Entity.TrainingPlan;
import com.example.GEEN.Repository.TrainingPlanRepository;
import com.example.GEEN.Service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: TrainingPlanServiceImpl
 * @projectName training-plan-service
 * @description: TODO
 * @date 2021/5/113:46
 */
@Service
public class TrainingPlanServiceImpl implements TrainingPlanService {
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Override
    public List<TrainingPlan> findPlanByNameAndDate(String accountName, Date date) {
        return trainingPlanRepository.findTrainingPlanByAccountNameAndDate(accountName,date);
    }

    @Override
    public List<TrainingPlan> findPlanByName(String accountName) {
        return trainingPlanRepository.findTrainingPlanByAccountName(accountName);
    }

    @Override
    public void deletePlan(Long id) {
        trainingPlanRepository.deleteById(id);
    }

    @Override
    public void changePlan(TrainingPlan trainingPlan) {
        trainingPlanRepository.save(trainingPlan);
    }

    @Override
    public Optional<TrainingPlan> findPlanById(Long id) {
        return trainingPlanRepository.findById(id);
    }
}
