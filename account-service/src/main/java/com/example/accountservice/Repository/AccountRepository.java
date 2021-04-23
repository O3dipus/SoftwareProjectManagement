package com.example.accountservice.Repository;

import com.example.accountservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findAccountByAccountName(String accountName);
    public List<Account> findAccountByAccountNameAndPassword(String accountName, String password);
}
