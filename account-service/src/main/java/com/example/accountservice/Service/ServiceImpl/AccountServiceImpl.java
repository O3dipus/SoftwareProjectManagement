package com.example.accountservice.Service.ServiceImpl;

import com.example.accountservice.Entity.Account;
import com.example.accountservice.Repository.AccountRepository;
import com.example.accountservice.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
