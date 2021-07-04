package com.example.GEEN.Repository;

import com.example.GEEN.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findAccountByAccountName(String accountName);
    public List<Account> findAccountByAccountNameAndPassword(String accountName, String password);
}
