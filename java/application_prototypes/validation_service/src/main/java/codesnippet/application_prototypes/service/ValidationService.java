package codesnippet.application_prototypes.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import codesnippet.application_prototypes.service.rule.Validatable;
import codesnippet.java_utility.Slf4jLogger;

public class ValidationService
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

    private final List<Validatable> validatorList;

    public ValidationService(List<Validatable> validatorList) {
        this.validatorList = validatorList;
    }

    public boolean validate(String password) {
        for(Validatable validator : this.validatorList) {
            LOGGER.info(validator.info());
            if(!validator.validate(password)) {
                return false;
            }
        }
        return true;
    }
}
