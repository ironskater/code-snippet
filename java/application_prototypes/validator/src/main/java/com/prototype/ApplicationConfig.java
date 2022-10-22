package com.prototype;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.prototype.service.ValidationService;
import com.prototype.service.rule.TextComponentValidator;
import com.prototype.service.rule.TextLengthValidator;
import com.prototype.service.rule.TextRepeatValidator;
import com.prototype.service.rule.Validatable;

@Configuration
public class ApplicationConfig
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
