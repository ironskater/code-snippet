package codesnippet.application_prototypes.service.rule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonDeserializer implements Validatable
{
    @Value("${rule.text.component}")
    private String textComponent;

    @Override
    public boolean validate(String password) {
        return password.matches(this.textComponent);
    }
}
