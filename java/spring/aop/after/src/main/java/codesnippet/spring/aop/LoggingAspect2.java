package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect2
{
    private static final Logger LOGGER = LogManager.getLogger();

    @After(value = "execution(* codesnippet.spring.aop.dao.AccountDao.findAccounts(..))")
    public void afterAdvice(JoinPoint joinpoint) {
        LOGGER.info("=================== after advices in Logging Aspect2");
        LOGGER.info("target method: " + joinpoint.getSignature().toShortString());
    }
}
