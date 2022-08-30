package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MembershipDao
{
    private static final Logger LOGGER = LogManager.getLogger();

    public void addAccount() {
        LOGGER.info("adding account .......................................");
    }

    public void addSillyMember() {
        LOGGER.info("add silly member..............................");
    }
}
