package com.example.GEEN.Service;

import com.example.GEEN.Entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAccountByName(String accountName);
    public List<Account> findAccountByNameAndPassword(String accountName, String password);
    public void saveAccount(Account account);
}
