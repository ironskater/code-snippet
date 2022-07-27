package codesnippet.application_prototypes.service;

import java.util.List;

import codesnippet.application_prototypes.service.rule.Validatable;

public class ValidationService
{
    private final List<Validatable> validatorList;

    public ValidationService(List<Validatable> validatorList) {
        this.validatorList = validatorList;
    }

    public boolean validate(String password) {
        for(Validatable validator : this.validatorList) {
            if(!validator.validate(password)) {
                return false;
            }
        }
        return true;
    }
}
