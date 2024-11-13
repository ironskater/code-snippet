package codesnippet.prototype.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(* codesnippet.prototype.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* codesnippet.prototype.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* codesnippet.prototype.persistence.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    private void beforeAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        LOGGER.info("===================== start execute beforeAdvice");
        LOGGER.info("@Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            LOGGER.info("argument: {}", arg);
        }
    }

    @AfterReturning(
        pointcut = "forAppFlow()",
        returning = "result"
    )
    private void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        LOGGER.info("===================== start execute afterReturningAdvice");
        LOGGER.info("@AfterReturning: calling method: " + method);

        LOGGER.info("@AfterReturning: result: " + result);
    }
}
