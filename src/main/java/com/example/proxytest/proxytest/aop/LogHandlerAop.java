package com.example.proxytest.proxytest.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @date 2019/4/25 18:30
 * @since 1.0
 */
@Component
@Aspect
@Log4j2
public class LogHandlerAop {


    /**
     * @return
     */
    @Before("within(com.example.proxytest..*) &&(@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void execute()throws Throwable {
//            log.info("代理:");
//            Object resultTargetObject = joinPoint.proceed();
//            return resultTargetObject;

    }

    /**
     *
     * @param joinPoint
     * @return
     */
//    @Before("within(com.example.proxytest..*) &&(@annotation(org.springframework.web.bind.annotation.PostMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.RequestMapping))")
//    public void execute(ProceedingJoinPoint joinPoint)throws Throwable {
////            log.info("代理:");
////            Object resultTargetObject = joinPoint.proceed();
////            return resultTargetObject;
//
//    }


}
