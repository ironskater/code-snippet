package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger();

    // "execution(public void addAccount())" is a pointcut expression
    // @Before("execution(public void addAccount())") // mathc all addAccount method
    // @Before("execution(public void codesnippet.spring.aop.AccountDao.addAccount())") // only match addAccount mehotd for the specific class
    // @Before("execution(public void add*())") // match method starting with "add"
    // can be any method name
    public void beforeAddAccountAdvice() {
        LOGGER.info("=============== executing @Before beforeAddAccountAdvice");
    }
}
