package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import codesnippet.spring.aop.dao.Account;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(* codesnippet.spring.aop.dao.*.add*(..))")
    private void forDaoPackage() {}

    @Before("forDaoPackage()") // refer to line 17 method name(note that the method name can be anyone)
    public void beforeAddAccountAdviceV1(JoinPoint joinpoint) {
        LOGGER.info("=============== beforeAddAccountAdviceV1: executing @Before beforeAddAccountAdvice");

        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();

        LOGGER.info("=============== method: " + methodSignature);

        Object[] args = joinpoint.getArgs();
        for(Object arg : args) {
            LOGGER.info("========================= arg: " + arg);
            if(arg instanceof Account) {
                Account account = (Account)arg;
                LOGGER.info("========================= account name: " + account.getName());
                LOGGER.info("========================= account password: " + account.getPassword());
            }
        }
    }
}
