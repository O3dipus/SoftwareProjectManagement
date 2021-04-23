package com.example.accountservice.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String accountName;
    public String userName;
    public String sex;
    public int age;
    public double weight;
    public double height;
    public double chestCircum;//胸围
    public double armCircum;//臂围
    public double hipCircum;//臀围
    public double waistline;//腰围

    public UserInfo() {


    }
    public UserInfo(String accountName, String userName, String sex, int age, double weight,
                    double height, double chestCircum, double armCircum, double hipCircum, double waistline) {
        this.accountName=accountName;
        this.userName=userName;
        this.sex=sex;
        this.age=age;
        this.weight=weight;
        this.height=height;
        this.chestCircum=chestCircum;
        this.armCircum=armCircum;
        this.hipCircum=hipCircum;
        this.waistline=waistline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getArmCircum() {
        return armCircum;
    }

    public double getChestCircum() {
        return chestCircum;
    }

    public double getHeight() {
        return height;
    }

    public double getHipCircum() {
        return hipCircum;
    }

    public double getWaistline() {
        return waistline;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArmCircum(double armCircum) {
        this.armCircum = armCircum;
    }

    public void setChestCircum(double chestCircum) {
        this.chestCircum = chestCircum;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setHipCircum(double hipCircum) {
        this.hipCircum = hipCircum;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWaistline(double waistline) {
        this.waistline = waistline;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
