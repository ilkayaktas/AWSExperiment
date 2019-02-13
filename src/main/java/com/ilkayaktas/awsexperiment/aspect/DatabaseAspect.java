package com.ilkayaktas.awsexperiment.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aselsan on 12.02.2019 at 18:38.
 */

@Aspect
@Configuration
public class DatabaseAspect {
    @After("execution(* *.saveUser(*))")
    public void getAllAdvice(){
        System.out.println("User saved");
    }
}
