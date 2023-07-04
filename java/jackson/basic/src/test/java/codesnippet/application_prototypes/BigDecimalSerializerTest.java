package codesnippet.application_prototypes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import codesnippet.application_prototypes.model.Rsp;

@SpringBootTest
public class BigDecimalSerializerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {

        Rsp rsp = new Rsp(BigDecimal.valueOf(0.00000001));

        String str = this.objectMapper.writeValueAsString(rsp);

        System.out.println(str);
    }
}
