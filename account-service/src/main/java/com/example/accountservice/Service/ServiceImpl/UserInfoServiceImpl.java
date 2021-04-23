package com.example.accountservice.Service.ServiceImpl;

import com.example.accountservice.Entity.UserInfo;
import com.example.accountservice.Repository.UserInfoRepository;
import com.example.accountservice.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> findUserInfoByName(String accountName) {
        return userInfoRepository.findUserInfoByAccountName(accountName);
    }

    @Override
    public void saveInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }
}
