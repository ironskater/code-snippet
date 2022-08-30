package codesnippet.spring.aop.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AccountDao
{
    private static final Logger LOGGER = LogManager.getLogger();

    private String name;
    private String code;

    public void addAccount() {
        LOGGER.info("adding an account............");
    }

    public String getName() {
        LOGGER.info("in getName..................");
        return name;
    }

    public void setName(String name) {
        LOGGER.info("in setName..................");
        this.name = name;
    }

    public String getCode() {
        LOGGER.info("in getCode..................");
        return code;
    }

    public void setCode(String code) {
        LOGGER.info("in setCode..................");
        this.code = code;
    }
}
