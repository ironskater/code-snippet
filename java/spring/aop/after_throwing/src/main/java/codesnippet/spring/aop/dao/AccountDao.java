package codesnippet.spring.aop.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AccountDao
{
    private static final Logger LOGGER = LogManager.getLogger();

    public void addAccount(Account account, boolean isUser) {
        LOGGER.info("adding an account............");
    }

    public List<Account> findAccounts(boolean tripWire) {

        if(tripWire) {
            throw new RuntimeException("I am runtime exception");
        }

        List<Account> accounts = new ArrayList<>();

        Account account1 = new Account("john", "pw1");
        Account account2 = new Account("mary", "pw2");
        Account account3 = new Account("bob", "pw3");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        return accounts;
    }
}
