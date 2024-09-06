package com.ben.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public boolean addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN MEMBER ACCOUNT");
        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": I am going to sleep now... ");
    }

}
