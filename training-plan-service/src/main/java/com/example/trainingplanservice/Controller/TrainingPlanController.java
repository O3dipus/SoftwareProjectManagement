package com.example.trainingplanservice.Controller;

import com.example.trainingplanservice.Entity.TrainingPlan;
import com.example.trainingplanservice.Service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: TrainingPlanController
 * @projectName training-plan-service
 * @description: TODO
 * @date 2021/5/113:54
 */
@RestController
@RequestMapping("/api/v1/")
public class TrainingPlanController {
    @Autowired
    private TrainingPlanService trainingPlanService;

    @RequestMapping("/trainingPlan")
    public List<TrainingPlan> findByNameAndDate(@RequestParam(value = "accountName", required = true) String accountName,
                                                @RequestParam(value = "time" , required = false) String time) {
        if(time==null) {
            return trainingPlanService.findPlanByName(accountName);
        }
        else{
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return trainingPlanService.findPlanByNameAndDate(accountName,date);
        }
    }

    @RequestMapping("/changeTrainingPlan")
    public void changePlan(@RequestParam(value = "id",required = true) Long id,
                           @RequestParam(value = "workoutName",required = true) String workoutName,
                           @RequestParam(value = "groupNum",required = true) double groupNum,
                           @RequestParam(value = "times",required = true) double times,
                           @RequestParam(value = "weight",required = true) double weight) {
        Optional<TrainingPlan> plan = trainingPlanService.findPlanById(id);
        if(plan!=null) {
            plan.get().setWorkoutName(workoutName);
            plan.get().setGroupNum(groupNum);
            plan.get().setTimes(times);
            plan.get().setWeight(weight);
            trainingPlanService.changePlan(plan.get());
        }
    }
    @RequestMapping("/createTrainingPlan")
    public void createPlan(@RequestParam(value = "accountName",required = true) String accountName,
                           @RequestParam(value = "workoutName",required = true) String workoutName,
                           @RequestParam(value = "groupNum",required = true) double groupNum,
                           @RequestParam(value = "times",required = true) double times,
                           @RequestParam(value = "weight",required = true) double weight) {
        TrainingPlan trainingPlan = new TrainingPlan(accountName,workoutName,groupNum,times,weight);
        trainingPlanService.changePlan(trainingPlan);
    }

    @RequestMapping("/deleteTrainingPlan/{id}")
    public void deletePlan(@PathVariable("id") Long id) {
        trainingPlanService.deletePlan(id);
    }

    @RequestMapping("/trainingPlan/capacity")
    public double getCapacity(@RequestParam(value = "accountName",required = true) String accountName,
                              @RequestParam(value = "time",required = true) String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<TrainingPlan> trainingPlans = trainingPlanService.findPlanByNameAndDate(accountName,date);
        double capacity=0;
        for(int i=0;i<trainingPlans.size();i++) {
            capacity+= trainingPlans.get(i).groupNum*trainingPlans.get(i).times*trainingPlans.get(i).weight;
        }
        return capacity;
    }
}
