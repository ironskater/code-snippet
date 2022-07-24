package codesnippet.application_prototypes.service.rule;

import org.springframework.beans.factory.annotation.Value;

public class TextLengthValidator implements Validatable
{
    @Value("${rule.text-len.min}")
    private int lenMin;

    @Value("${rule.text-len.max}")
    private int lenMax;

    @Override
    public boolean validate(String password) {
        return this.lenMax >= password.length() && password.length() >= this.lenMin;
    }

    @Override
    public String info() {
        return "I am TextLengthValidator";
    }

    public int getLenMax() {
        return lenMax;
    }

    public int getLenMin() {
        return lenMin;
    }
}
