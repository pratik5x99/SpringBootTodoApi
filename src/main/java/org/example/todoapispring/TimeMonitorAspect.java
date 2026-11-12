package org.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint) throws Throwable  {
        long startTime = System.currentTimeMillis();

        try {
            // execute the join point
            joinPoint.proceed();
        }
        catch (Throwable ignored) {
            System.out.println("Something went wrong");
        }
        finally {
            long endTime = System.currentTimeMillis();

            long totalTime = endTime - startTime;

            System.out.println("Total execution time of this method is : " + totalTime + " ms");
        }

    }
}
