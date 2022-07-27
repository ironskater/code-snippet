package codesnippet.application_prototypes.service.rule;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;

public class TextLengthValidator implements Validatable
{
    @Min(value = 0)
    @Value("${rule.text.len.min}")
    private int lenMin;

    @Value("${rule.text.len.max}")
    private int lenMax;

    @Override
    public boolean validate(String password) {
        return this.lenMax >= password.length() && password.length() >= this.lenMin;
    }
}
