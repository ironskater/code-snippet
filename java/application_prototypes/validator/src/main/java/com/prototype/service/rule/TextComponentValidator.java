package com.prototype.service.rule;

import org.springframework.beans.factory.annotation.Value;

public class TextComponentValidator implements Validatable
{
    @Value("${rule.text.component}")
    private String textComponent;

    @Override
    public boolean validate(String password) {
        return password.matches(this.textComponent);
    }
}
