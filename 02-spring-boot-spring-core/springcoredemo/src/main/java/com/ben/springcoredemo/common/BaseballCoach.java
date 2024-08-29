package com.ben.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice";
    }

    public BaseballCoach(){
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }
}
