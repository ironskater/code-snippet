package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import codesnippet.spring.aop.dao.AccountDao;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AccountDao accountDao;

    public static void main(String[] args) {
        LOGGER.info("LOGGER: ============= start spring application.........");
        System.out.println("SYSOUT: ============= start spring application.........");
        SpringApplication.run(App.class);
        LOGGER.info("============= end spring application.........");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("============= executing commmandline runner");
        this.accountDao.addAccount();
        this.accountDao.addAccount();
        this.accountDao.setName("foobar");
        this.accountDao.setCode("silver");
        String name = this.accountDao.getName();
        String code = this.accountDao.getCode();
        LOGGER.info(System.lineSeparator());

    }
}
