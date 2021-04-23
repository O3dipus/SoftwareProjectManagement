package com.example.accountservice.Controller;

import com.example.accountservice.Entity.Account;
import com.example.accountservice.Entity.RandomUtil;
import com.example.accountservice.Entity.UserInfo;
import com.example.accountservice.Service.AccountService;
import com.example.accountservice.Service.MsmService;
import com.example.accountservice.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MsmService msmService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/register")
    public Boolean register(@RequestParam("accountName") String accountName,
                            @RequestParam("password") String password) {
        List<Account> account = accountService.findAccountByName(accountName);

        if(account.isEmpty()) {
            Account newAccount = new Account(accountName,password);
            accountService.saveAccount(newAccount);
            //级联插入
            String userName = "用户"+accountName;
            UserInfo userInfo = new UserInfo(accountName,userName,"男",18,0.00,0.00,0.00,0.00,0.00,0.00);
            userInfoService.saveInfo(userInfo);
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping("/login")
    public Boolean login(@RequestParam("accountName") String accountName,
                         @RequestParam("password") String password) {
        List<Account> account = accountService.findAccountByNameAndPassword(accountName,password);
        if(account.size()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping("/changePassword")
    public Boolean changePassword(@RequestParam("accountName") String accountName,
                                  @RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword) {
        List<Account> account = accountService.findAccountByNameAndPassword(accountName,oldPassword);
        if(account.size()>0) {
            account.get(0).setPassword(newPassword);
            accountService.saveAccount(account.get(0));
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("email") String email) {
        String code = RandomUtil.getFourBitRandom();
        String title = "注册验证码";
        Boolean success = msmService.send(email,title,code);
        if(success) {
            return code;
        }
        else {
            return "发送失败";
        }
    }

}
