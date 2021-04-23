package com.example.accountservice.Service;

import com.example.accountservice.Entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> findAccountByName(String accountName);
    public List<Account> findAccountByNameAndPassword(String accountName, String password);
    public void saveAccount(Account account);
}
