package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // setup logger

    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    // do same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        // display method we are calling
        String method = joinPoint.getSignature().toShortString();

        myLogger.info("====>>>> in @Before: calling method: "+method);

        //display the argument to method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop thru and display
        for(Object arg: args){
            myLogger.info("====>>>>: Arguments " + arg);
        }
    }

    // add @AfterReturning advice

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "results"
    )
    public void afterReturning(JoinPoint joinPoint, Object results){
        // display method we are return from
        String method = joinPoint.getSignature().toShortString();

        myLogger.info("====>>>> in @AfterReturning: calling method: "+method);

        // display the data returned
        myLogger.info("====>>>> result: "+results);
    }
}
