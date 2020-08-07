package com.example.producer.customer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccubee
 * @since 20-1-9 21:36
 */
@Aspect
@Configuration
public class LoginAspect {

    @Around("execution(* com.example.customer.*(..))")
    public Object adminController(ProceedingJoinPoint joinPoint){
        return null;
    }
}
