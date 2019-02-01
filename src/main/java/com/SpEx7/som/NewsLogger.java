package com.SpEx7.som;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

public class NewsLogger {
    private final Logger logger = LogManager.getRootLogger();

    private void addLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object has been added : " + obj.toString());
        }
    }

    private void updateLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object has been updated : " + obj.toString());
        }
    }

    private void deleteLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            logger.info("The object with id: " + obj.toString() + " has been deleted");
        }
    }
}
