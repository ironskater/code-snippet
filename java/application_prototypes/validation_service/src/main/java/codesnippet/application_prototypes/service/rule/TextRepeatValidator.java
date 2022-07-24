package codesnippet.application_prototypes.service.rule;

import java.lang.invoke.MethodHandles;

import codesnippet.java_utility.Slf4jLogger;

public class TextRepeatValidator implements Validatable
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean validate(String password) {
        return !this.hasRepeatPattern(password, 1);
    }

    private boolean hasRepeatPattern(String password, int windowSize) {
        if(windowSize > password.length()/2) {
            return false;
        }

        for(int ix = windowSize * 2; ix <= password.length(); ix++) {
            String firstSubStr = password.substring(ix - windowSize * 2, ix - windowSize);
            String secondSubStr = password.substring(ix - windowSize, ix);

            if(firstSubStr.equals(secondSubStr)) {
                LOGGER.info("firstSubStr: " + firstSubStr);
                LOGGER.info("secondSubStr: " + secondSubStr);
                return true;
            }
        }

        return this.hasRepeatPattern(password, ++windowSize);
    }

    @Override
    public String info() {
        return "I am TextRepeatValidator";
    }
}
