package codesnippet.application_prototypes.model;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
public class StudentTest {

    @Autowired
    private ObjectMapper objMapper;

    @Test
    public void deserialize_thenSeriealize_shouldSucceed() throws IOException {

        // Arrange
        ObjectMapper objMapper = new ObjectMapper();

        // Act
        Student actual =
                objMapper.readValue(new File("./src/test/resources/student.json"), Student.class);

        System.out.println(objMapper.getDeserializationConfig());

        objMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String output = objMapper.writeValueAsString(actual);

        System.out.println(output);

        // Assert
    }
}
