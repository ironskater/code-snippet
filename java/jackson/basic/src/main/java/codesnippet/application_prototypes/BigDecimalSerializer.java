package codesnippet.application_prototypes;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            gen.writeString(value.stripTrailingZeros().toPlainString());
        }
    }
}
