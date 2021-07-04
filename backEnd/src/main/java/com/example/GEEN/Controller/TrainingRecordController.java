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

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class TrainingRecordController {
    @Autowired
    private TrainingRecordService trainingRecordService;
    @Autowired
    private TrainingPlanService trainingPlanService;
    @RequestMapping("/trainingRecord")
    public List<TrainingRecord> getRecord(@RequestParam(value = "accountName",required = true) String accountName,
                                          @RequestParam(value = "category", required = true) String category,
                                          @RequestParam(value = "time",required = false) String time) {
        if (time==null) {
            return trainingRecordService.findTrainingRecordByAccountName(accountName,category);
        }
        else{
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return trainingRecordService.findTrainingRecordByAccountNameAndTime(accountName,date);
        }
    }
    @RequestMapping("/trainingRecord/{id}")
    public List<TrainingPlan> getRecordById(@PathVariable("id")Long id) {
        Optional<TrainingRecord> trainingRecord = trainingRecordService.findTrainingRecordById(id);
        if (!trainingRecord.isEmpty()) {
            String accountName = trainingRecord.get().getAccountName();
            Date date = trainingRecord.get().getDate();
            return trainingPlanService.findPlanByNameAndDate(accountName,date);
        }
        else {
            return null;
        }
    }
    @RequestMapping("/addTrainingRecord")
    public String addRecord(@RequestParam(value = "accountName",required = true)String accountName,
                             @RequestParam(value = "category",required = true)String category,
                             @RequestParam(value = "capacity",required = true)Double capacity,
                            @RequestParam(value = "time",required = true)String time) {


        if (!category.equals("胸") && !category.equals("腹") && !category.equals("背") && !category.equals("腿") &&
                !category.equals("肩") && !category.equals("三头") && !category.equals("二头")) {
            return "wrong category!";
        }
        else if (capacity.doubleValue()<0==true) {
            return "wrong capacity!";
        }
        else {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<TrainingRecord> trainingRecords = trainingRecordService.findTrainingRecordByAccountNameAndTime(accountName,date);
            if(trainingRecords.size()>0) {
                TrainingRecord trainingRecord = trainingRecords.get(0);
                trainingRecord.setCapacity(capacity);
                trainingRecord.setCategory(category);
                trainingRecordService.addTrainingRecord(trainingRecord);
                return "update successfully!";
            }
            else {
                TrainingRecord trainingRecord = new TrainingRecord(accountName,category,capacity);
                trainingRecordService.addTrainingRecord(trainingRecord);
                return "add successfully!";
            }

        }
    }

    @RequestMapping("/deleteTrainingRecord/{id}")
    public String delRecord(@PathVariable("id")Long id) {
        Optional<TrainingRecord> trainingRecord = trainingRecordService.findTrainingRecordById(id);
        if (trainingRecord.isEmpty()) {
            return "invalid record id!";
        }
        else {
            Date date = trainingRecord.get().getDate();
            String accountName = trainingRecord.get().getAccountName();
            List<TrainingPlan> plans = trainingPlanService.findPlanByNameAndDate(accountName,date);
            for(TrainingPlan plan:plans) {
                trainingPlanService.deletePlan(plan.getId());
            }
            trainingRecordService.deleteTrainingPlan(id);
            return "delete successfully!";
        }
    }
}
