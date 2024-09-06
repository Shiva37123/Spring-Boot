package com.ben.aopdemo.dao;

import com.ben.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    public List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);
}