package com.example.GEEN.Controller;

import com.example.GEEN.Entity.TrainingPlan;
import com.example.GEEN.Entity.TrainingRecord;
import com.example.GEEN.Service.TrainingPlanService;
import com.example.GEEN.Service.TrainingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
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
    public String changePlan(@RequestParam(value = "id",required = true) Long id,
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
            return "update successfully!";
        }
        else {
            return "invalid id!";
        }
    }
    @RequestMapping("/createTrainingPlan")
    public String createPlan(@RequestParam(value = "accountName",required = true) String accountName,
                           @RequestParam(value = "workoutName",required = true) String workoutName,
                           @RequestParam(value = "groupNum",required = true) double groupNum,
                           @RequestParam(value = "times",required = true) double times,
                           @RequestParam(value = "weight",required = true) double weight) {
        TrainingPlan trainingPlan = new TrainingPlan(accountName,workoutName,groupNum,times,weight);
        trainingPlanService.changePlan(trainingPlan);
        return "create successfully!";
    }

    @RequestMapping("/deleteTrainingPlan/{id}")
    public String deletePlan(@PathVariable("id") Long id) {
        Optional<TrainingPlan> plan = trainingPlanService.findPlanById(id);
        if(plan!=null) {
            trainingPlanService.deletePlan(id);
            return "delete successfully!";
        }
        else {
            return "invalid id!";
        }

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
