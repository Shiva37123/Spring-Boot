package com.ben.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BenAopExpressions {
    @Pointcut("execution(* com.ben.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    @Pointcut("execution(* com.ben.aopdemo.dao.*.get*(..))")
    public void getter(){}

    @Pointcut("execution(* com.ben.aopdemo.dao.*.set*(..))")
    public void setter(){}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterAndSetter(){}
}
