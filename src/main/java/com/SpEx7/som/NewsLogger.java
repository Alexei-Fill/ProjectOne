package com.SpEx7.som;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class NewsLogger {
    private final Logger logger = LogManager.getRootLogger();

    @Before(" execution(* add*(*))&& within(com.SpEx7.DAO.*)")
    private void addLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object has been added : " + obj.toString());
        }
    }

    @Before(" execution(* update*(*))&& within(com.SpEx7.DAO.*)")
    private void updateLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object has been updated : " + obj.toString());
        }
    }

    @Before(" execution(* delete*(*)) && within(com.SpEx7.DAO.*)")
    private void deleteLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object number: " + obj.toString() + " has been deleted");
        }
    }
}
