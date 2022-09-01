package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Around("execution(* codesnippet.spring.aop.service.TrafficService.getTraffic(..))")
    public Object aroundAdviceForHandlingException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("=========== start executing aroundAdvice");
        LOGGER.info("target object: " + proceedingJoinPoint.getSignature().toShortString());

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            LOGGER.warn(ex.getMessage());

            // we can handle exception at here
            result = "Major Accident! But no Worries!!!!!!";
            // or rethrow it
            // throw new RuntimeException(ex);
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        LOGGER.info("The duration is: " + duration + "ms");

        return result;
    }
}
