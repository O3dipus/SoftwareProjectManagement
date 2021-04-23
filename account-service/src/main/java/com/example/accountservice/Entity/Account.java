package com.example.accountservice.Entity;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String accountName;
    public String password;

    public Account(){

    }

    public Account(String accountName,String password) {
        this.accountName=accountName;
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
