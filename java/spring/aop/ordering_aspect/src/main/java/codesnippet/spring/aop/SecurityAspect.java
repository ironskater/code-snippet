package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class SecurityAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(* codesnippet.spring.aop.dao.*.*(..))")
    private void forDaoPackage() {}

    @Before("forDaoPackage()")
    public void beforeAddAccountAdviceForSecurity() {
        LOGGER.info("=============== beforeAddAccountAdviceSecurity: executing @Before beforeAddAccountAdvice");
    }
}
