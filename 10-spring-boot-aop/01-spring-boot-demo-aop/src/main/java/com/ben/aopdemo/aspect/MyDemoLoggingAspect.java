package com.ben.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // this is where we add all of our related advice for logging

    // let's start with annotation @Before advice

    @Before("execution(* com.ben.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=======>>>> Executing @Before advice on addAccount()");
    }
}
