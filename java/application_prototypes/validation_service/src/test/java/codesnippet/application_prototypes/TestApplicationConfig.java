package codesnippet.application_prototypes;

import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import codesnippet.application_prototypes.service.ValidationService;
import codesnippet.application_prototypes.service.rule.TextComponentValidator;
import codesnippet.application_prototypes.service.rule.TextLengthValidator;
import codesnippet.application_prototypes.service.rule.TextRepeatValidator;
import codesnippet.application_prototypes.service.rule.Validatable;

@TestConfiguration
public class TestApplicationConfig
{
    @Bean
    @Order(1)
    public Validatable textComponentValidator() {
        return new TextComponentValidator();
    }

    @Bean
    @Order(2)
    public Validatable textLengthValidator() {
        return new TextLengthValidator();
    }

    @Bean
    @Order(3)
    public Validatable textRepeatValidator() {
        return new TextRepeatValidator();
    }

    @Bean
    public ValidationService validationService(List<Validatable> validatorList) {
        return new ValidationService(validatorList);
    }
}
