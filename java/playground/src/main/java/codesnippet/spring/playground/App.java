package codesnippet.spring.playground;

public class App {

    static class Account{
        double getBalance(){
           return 0;
        }
    }

    static class SavingsAccount extends Account {
        double getSavings() {
            return 100;
        }
    }
    static class TermAccount extends Account {
        double getTermAccount() {
            return 1000;
        }
    }
    static class CurrentAccount extends Account {
        double getCurrentAccount() {
            return 10000;
        }
    }

    public static void main(String[] args) {

        System.out.println(processInputNew("yess"));
    }

    static double getBalanceWithSwitchPattern(Account account) {
        double result = 0;
        switch (account) {
            case null -> throw new RuntimeException("Oops, account is null");
            case SavingsAccount sa -> result = sa.getSavings();
            case TermAccount ta -> result = ta.getTermAccount();
            case CurrentAccount ca -> result = ca.getCurrentAccount();
            default -> result = account.getBalance();
        };
        return result;
    }

    static String processInputNew(String input) {
        String output = null;
        switch(input) {
            case null -> output = "Oops, null";
            case String s when "Yes".equalsIgnoreCase(s) -> output = "It's Yes";
            case String s when "No".equalsIgnoreCase(s) -> output = "It's No";
            default -> output = "Unknown input";
        }
        return output;
    }
}
