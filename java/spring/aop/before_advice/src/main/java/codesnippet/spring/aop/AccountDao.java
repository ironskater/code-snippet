package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AccountDao
{
    private static final Logger LOGGER = LogManager.getLogger();

    public void addAccount() {
        LOGGER.info("adding an account............");
    }
}
