package com.example.GEEN.Repository;

import com.example.GEEN.Entity.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author nyx
 * @title: DietPlanRepository
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:11
 */
@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan,Long> {

    public List<DietPlan> findDietPlanByAccountNameAndDate(String accountName, Date date);
    public List<DietPlan> findDietPlanByAccountName(String accountName);
    public List<DietPlan> findDietPlanByDate(Date date);
}
