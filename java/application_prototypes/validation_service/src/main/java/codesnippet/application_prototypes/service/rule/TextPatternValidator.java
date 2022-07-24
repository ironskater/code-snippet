package codesnippet.application_prototypes.service.rule;

import org.springframework.beans.factory.annotation.Value;

public class TextPatternValidator implements Validatable
{
    @Value("${rule.text-pattern}")
    private String textPattern;

    @Override
    public boolean validate(String password) {
        return password.matches(this.textPattern);
    }

    @Override
    public String info() {
        return "I am TextTypeValidator";
    }
}
