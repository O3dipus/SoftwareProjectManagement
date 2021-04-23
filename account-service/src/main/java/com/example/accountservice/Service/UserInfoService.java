package com.example.accountservice.Service;

import com.example.accountservice.Entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    public List<UserInfo> findUserInfoByName(String accountName);
    public void saveInfo(UserInfo userInfo);
}
