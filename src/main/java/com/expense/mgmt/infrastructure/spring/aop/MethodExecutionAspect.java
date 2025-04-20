package com.expense.mgmt.infrastructure.spring.aop;

import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.infrastructure.spring.annotation.LogExecutionTime;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
// module contains logging (cross-cutting) aspect
public class MethodExecutionAspect {

    // Match ALL public methods inside the application package & subpackages
    @Pointcut("execution(public * com.expense.mgmt.application..*(..))")
    public void applicationLayerMethods() {
    }

    @Pointcut(
            "within(@org.springframework.stereotype.Service *) || " +
            "within(@org.springframework.stereotype.Repository *) || " +
            "within(@org.springframework.stereotype.Controller *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)"
    )
    public void applicationLayerBeans() {
    }

    // Log execution time
    @Around("applicationLayerBeans()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return processJoinPoint(joinPoint);
    }

    @Around("@annotation(com.expense.mgmt.infrastructure.spring.annotation.LogExecutionTime)")
    public Object logExecution(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        return processJoinPoint(joinPoint);
    }

    private Object processJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}.{} executed in {} ms", className, methodName, (endTime - startTime));
        return result;
    }
}
