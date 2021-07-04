package com.example.GEEN.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author nyx
 * @title: TrainingPlan
 * @projectName training-plan-service
 * @description: TODO
 * @date 2021/5/113:24
 */
@Entity
@Table(name = "training_plan")
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "account_name")
    public String accountName;
    @Column(name = "workout_name")
    public String workoutName;
    @Column(name = "group_num")
    public double groupNum;
    @Column(name = "times")
    public double times;
    @Column(name = "weight")
    public double weight;

    @CreationTimestamp
    @Column(name = "date")
    public Date date;

    public TrainingPlan(){

    }
    public TrainingPlan(String accountName, String workoutName, double groupNum, double times, double weight) {
        this.accountName=accountName;
        this.workoutName=workoutName;
        this.groupNum=groupNum;
        this.times=times;
        this.weight=weight;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getWeight() {
        return weight;
    }

    public Date getDate() {
        return date;
    }

    public double getGroupNum() {
        return groupNum;
    }

    public Long getId() {
        return id;
    }

    public double getTimes() {
        return times;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGroupNum(double groupNum) {
        this.groupNum = groupNum;
    }

    public void setTimes(double times) {
        this.times = times;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
