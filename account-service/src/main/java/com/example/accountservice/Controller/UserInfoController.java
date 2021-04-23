package com.example.accountservice.Controller;

import com.example.accountservice.Entity.UserInfo;
import com.example.accountservice.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getInfo/{accountName}")
    public UserInfo getInfo(@PathVariable("accountName") String accountName) {
        List<UserInfo> user = userInfoService.findUserInfoByName(accountName);
        return user.get(0);
    }

    @RequestMapping("/changeInfo")
    public void changeInfo(@RequestParam(value = "accountName",required = true) String accountName,
                           @RequestParam(value = "userName",required = true) String userName,
                           @RequestParam(value = "sex",required = true) String sex,
                           @RequestParam(value = "age",required = true) int age,
                           @RequestParam(value = "weight",required = true) double weight,
                           @RequestParam(value = "height",required = true) double height,
                           @RequestParam(value = "chestCircum",required = true) double chestCircum,
                           @RequestParam(value = "armCircum",required = true) double armCircum,
                           @RequestParam(value = "hipCircum",required = true) double hipCircum,
                           @RequestParam(value = "waistline",required = true) double waistline) {
        List<UserInfo> user = userInfoService.findUserInfoByName(accountName);
        user.get(0).setAccountName(accountName);
        user.get(0).setUserName(userName);
        user.get(0).setSex(sex);
        user.get(0).setAge(age);
        user.get(0).setWeight(weight);
        user.get(0).setHeight(height);
        user.get(0).setChestCircum(chestCircum);
        user.get(0).setArmCircum(armCircum);
        user.get(0).setHipCircum(hipCircum);
        user.get(0).setWaistline(waistline);
        userInfoService.saveInfo(user.get(0));
    }
}
