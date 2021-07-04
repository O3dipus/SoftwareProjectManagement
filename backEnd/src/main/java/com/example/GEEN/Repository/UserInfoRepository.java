package com.example.GEEN.Repository;

import com.example.GEEN.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findUserInfoByAccountName(String accountName);

}
