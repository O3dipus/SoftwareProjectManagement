package com.example.GEEN.Controller;

import com.example.GEEN.Entity.Account;
import com.example.GEEN.Entity.RandomUtil;
import com.example.GEEN.Entity.UserInfo;
import com.example.GEEN.Service.AccountService;
import com.example.GEEN.Service.MsmService;
import com.example.GEEN.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
    public String register(@RequestParam("accountName") String accountName,
                            @RequestParam("password") String password) {
        List<Account> account = accountService.findAccountByName(accountName);

        if(account.isEmpty()) {
            boolean matches = accountName.matches("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}");
            if (matches==false) {
                return "invalid mail";
            }
            else {
                Account newAccount = new Account(accountName, password);
                accountService.saveAccount(newAccount);
                //级联插入
                String userName = "用户" + accountName;
                UserInfo userInfo = new UserInfo(accountName, userName, "男", 18, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00);
                userInfoService.saveInfo(userInfo);
                return "success";
            }
        }
        else {
            return "account existed";
        }
    }

    @RequestMapping("/login")
    public String login(@RequestParam("accountName") String accountName,
                         @RequestParam("password") String password) {
        boolean matches = accountName.matches("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}");
        if (matches==false) {
            return "invalid mail!";
        }
        else {
            List<Account> account = accountService.findAccountByNameAndPassword(accountName,password);
            if(account.size()>0) {
                return "login successfully!";
            }
            else {
                return "wrong password!";
            }
        }

    }

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("accountName") String accountName,
                                  @RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword) {
        List<Account> account = accountService.findAccountByNameAndPassword(accountName,oldPassword);
        if(account.size()>0) {
            account.get(0).setPassword(newPassword);
            accountService.saveAccount(account.get(0));
            return "success";
        }
        else {
            return "wrong password or invalid account!";
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
