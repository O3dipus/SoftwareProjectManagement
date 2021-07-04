package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Entity.Account;
import com.example.GEEN.Repository.AccountRepository;
import com.example.GEEN.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAccountByName(String accountName) {
        return accountRepository.findAccountByAccountName(accountName);
    }

    @Override
    public List<Account> findAccountByNameAndPassword(String accountName, String password) {
        return accountRepository.findAccountByAccountNameAndPassword(accountName,password);

    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
