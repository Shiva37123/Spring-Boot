package com.ben.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    public TennisCoach(){
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }
}
