package com.ben.aopdemo.dao;

import com.ben.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();
}
