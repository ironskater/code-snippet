package codesnippet.application_prototypes.service.rule;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import codesnippet.application_prototypes.model.ExtendableBean;

@SpringBootTest
public class JsonDeserializerTest
{
    @Autowired
    private ObjectMapper objMapper;

    @Test
    public void serialize_shouldSucceed() throws JsonProcessingException {

        // Arrange
        ExtendableBean bean = new ExtendableBean("myBean", new HashMap<>());
        bean.getProperties().put("attr1", "value1");
        bean.getProperties().put("attr2", "value2");

        // Act
        String result = this.objMapper.writeValueAsString(bean);

        // Assert
        System.out.println(result);
    }

    @Test
    public void deserialize_withoutJsonAnyGetter_shouldSucceed() throws JsonProcessingException
    {
        // Arrange
        String jsonStr = "{\"name\":\"myBean\",\"properties\":{\"attr2\":\"value2\",\"attr1\":\"value1\"}}";

        // Act
        ExtendableBean bean = this.objMapper.readerFor(ExtendableBean.class).readValue(jsonStr);

        // Assert
        System.out.println(bean);
    }

    @Test
    public void deserialize_with_JsonAnyGetter_shouldSucceed() throws JsonProcessingException
    {
        // Arrange
        String jsonStr = "{\"name\":\"myBean\",\"attr2\":\"value2\",\"attr1\":\"value1\"}";

        // Act
        ExtendableBean bean = this.objMapper.readerFor(ExtendableBean.class).readValue(jsonStr);

        // Assert
        System.out.println(bean);
    }
}
