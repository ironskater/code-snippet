package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private MembershipDao membershipDao;

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
        LOGGER.info(System.lineSeparator());
        this.membershipDao.addAccount();
        this.membershipDao.addAccount();
        LOGGER.info(System.lineSeparator());
        this.membershipDao.addSillyMember();
    }
}
