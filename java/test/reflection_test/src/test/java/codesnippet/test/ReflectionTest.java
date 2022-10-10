package codesnippet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import codesnippet.test.dao.ApplicationDao;
import codesnippet.test.model.CollegeStudent;
import codesnippet.test.model.StudentGrades;
import codesnippet.test.service.ApplicationService;

@SpringBootTest
public class ReflectionTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CollegeStudent studentOne;

    @Autowired
    private StudentGrades studentGrades;

    // Create mock for Dao
    @MockBean
    private ApplicationDao applicationDao;

    // Here we can autowire dependencies with interface
    @Autowired
    private ApplicationService applicationServiceImpl;

    @BeforeEach
    public void beforeEach() {
        this.studentOne.setFirstname("hyde");
        this.studentOne.setLastname("liao");
        this.studentOne.setEmailAddress("hydeliao@mail.com");
        this.studentOne.setStudentGrades(this.studentGrades);

        ReflectionTestUtils.setField(this.studentOne, "id", 1);
        ReflectionTestUtils.setField(this.studentOne, "studentGrades", new StudentGrades(new ArrayList<>(Arrays.asList(
            100.0, 85.0, 76.50, 91.75))));
    }

    @DisplayName("Throw runtime error")
    @Test
    public void getPrivateField() {
        assertEquals(1, ReflectionTestUtils.getField(this.studentOne, "id"));
    }

    @Test
    public void invokePrivateMethod() {
        assertEquals("hyde 1", ReflectionTestUtils.invokeMethod(this.studentOne, "getFirstNameAndId"));
    }
}
