package com.ben.aopdemo.aspect;

import com.ben.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.ben.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Exception {

        //print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();

        System.out.println("\n====>>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        Object result=null;
        //now, let's execute the method
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {

            // log the exception
            System.out.println(exc.getMessage());

            // rethrow the exception

            throw exc;

//            // give a user a custom message
//            result = "Major accident! But no worries, your private AOP helicopter is on the way!";

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end-begin;
        System.out.println("\n====>>>> Duration: " + duration/1000 +" seconds");

        return result;
    }

    @After("execution(* com.ben.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n====>>>> Executing @AfterFinally on method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.ben.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExc){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n====>>>> Executing @AfterThrowing on method: " + method);

        //log the exception
        System.out.println("\n====>>>> Exception is " + theExc);

    }


    //add new advice for after for @AfterReturning on the findAccounts Method

    @AfterReturning(
            pointcut = "execution(* com.ben.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "results"
    )
    public void afterReturningFindAccount(JoinPoint joinPoint, List<Account> results){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n====>>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n====>>>> result is: "+results);

        System.out.println("Modifying Name to Upper Case");
        convertAccountNamToUpperCase(results);
        System.out.println("\n====>>>> result is: "+results);


    }

    private void convertAccountNamToUpperCase(List<Account> results) {
        for(Account account: results){
            String theUpperName = account.getName().toUpperCase();
            account.setName(theUpperName);
        }

    }
    // this is where we add all of our related advice for logging

    // let's start with annotation @Before advice


    @Before("com.ben.aopdemo.aspect.BenAopExpressions.forDaoPackageNoGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
        System.out.println("\n=======>>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = theJointPoint.getArgs();

        //loop through args
        for(Object tempArgs: args){
            System.out.println(tempArgs);

            if( tempArgs instanceof Account){
                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArgs;

                System.out.println("Account Name: "+ theAccount.getName());
                System.out.println("Account level: "+ theAccount.getLevel());
            }
        }

    }




}
