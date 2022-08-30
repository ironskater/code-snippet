package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(* codesnippet.spring.aop.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* codesnippet.spring.aop.dao.*.get*(..))")
    private void getter() {}

    @Pointcut("execution(* codesnippet.spring.aop.dao.*.set*(..))")
    private void setter() {}

    @Before("forDaoPackage()") // refer to line 17 method name(note that the method name can be anyone)
    public void beforeAddAccountAdviceV1() {
        LOGGER.info("=============== beforeAddAccountAdviceV1: executing @Before beforeAddAccountAdvice");
    }

    @Before("forDaoPackage() && !(getter() || setter())")
    public void beforeAddAccountAdviceV2() {
        LOGGER.info("=============== beforeAddAccountAdviceV2: executing @Before beforeAddAccountAdvice");
    }
}
