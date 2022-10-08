package codesnippet.test;

import java.text.MessageFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.name}")
    private String appName;

    @Test
    public void test() {

        System.out.println(MessageFormat.format("school name: [{0}]", this.schoolName));
        System.out.println(MessageFormat.format("app name: [{0}]", this.appName));
    }
}
