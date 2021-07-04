package com.example.GEEN.Repository;

import com.example.GEEN.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nyx
 * @title: FoodRepository
 * @projectName diet-plan-service
 * @description: TODO
 * @date 2021/4/2315:57
 */
@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    public List<Food> findFoodByName(String foodName);
}
