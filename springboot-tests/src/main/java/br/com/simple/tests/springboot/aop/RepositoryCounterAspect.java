package br.com.simple.tests.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryCounterAspect {

    private final CounterService counterService;

    @Autowired
    public RepositoryCounterAspect(CounterService counterService) {
        this.counterService = counterService;
    }

    @AfterReturning(pointcut = "execution(* br.com.simple.tests.springboot.model.*Repository.*(..))")
    public void afterSave(JoinPoint jp) {
        counterService.increment("repository.calls." + jp.getSignature().getDeclaringType().getSimpleName() + "." + jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* br.com.simple.tests.springboot.model.*Repository.*(..))", throwing = "e")
    public void afterGetGreetingThrowsException(JoinPoint jp, Exception e) {
        counterService.increment("repository.errors." + jp.getSignature().getDeclaringType().getSimpleName() + "." + jp.getSignature().getName());
    }
}
