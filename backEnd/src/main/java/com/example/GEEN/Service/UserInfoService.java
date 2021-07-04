package com.example.GEEN.Service;

import com.example.GEEN.Entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    public List<UserInfo> findUserInfoByName(String accountName);
    public void saveInfo(UserInfo userInfo);
}
