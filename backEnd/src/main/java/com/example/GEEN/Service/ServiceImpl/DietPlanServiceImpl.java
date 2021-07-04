package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Entity.DietPlan;
import com.example.GEEN.Repository.DietPlanRepository;
import com.example.GEEN.Service.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: DietPlanServiceImpl
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:28
 */
@Service
public class DietPlanServiceImpl implements DietPlanService {
    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Override
    public Optional<DietPlan> findPlanById(Long id) {
        return dietPlanRepository.findById(id);
    }

    @Override
    public List<DietPlan> findPlanByNameAndTime(String accountName, Date date) {
        return dietPlanRepository.findDietPlanByAccountNameAndDate(accountName, date);

    }

    @Override
    public List<DietPlan> findPlanByName(String accountName) {
        return dietPlanRepository.findDietPlanByAccountName(accountName);
    }

    @Override
    public void deletePlanById(Long id) {
        dietPlanRepository.deleteById(id);
    }

    @Override
    public void changePlan(DietPlan plan) {
        dietPlanRepository.save(plan);
    }

    @Override
    public List<DietPlan> findByTime(Date date) {
        return dietPlanRepository.findDietPlanByDate(date);
    }
}
