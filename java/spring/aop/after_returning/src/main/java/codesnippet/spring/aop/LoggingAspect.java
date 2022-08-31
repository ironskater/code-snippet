package codesnippet.spring.aop;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import codesnippet.spring.aop.dao.Account;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    @AfterReturning(pointcut = "execution(* codesnippet.spring.aop.dao.AccountDao.findAccounts(..))",
                    returning = "results")
    public void afterReturningAdvice(JoinPoint joinpoint, List<Account> results) {
        String method = joinpoint.getSignature().toShortString();
        LOGGER.info("=================== after returning advices");
        LOGGER.info("target method: " + method);
        LOGGER.info("oringal results are: " + results);

        for(Account e : results) {
            e.setName(e.getName().toUpperCase());
        }
    }
}
