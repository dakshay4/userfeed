package com.dakshay.userfeed.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AffectionAspectInterceptor {

    @Around("@annotation(AffectionPostProcessor)")
    public Object processAffection(ProceedingJoinPoint joinPoint) {
        return null;
    }
}
