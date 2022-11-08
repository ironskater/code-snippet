package codesnippet.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import codesnippet.test.model.CollegeStudent;
import codesnippet.test.model.ImmutableStudent;

public class ReflectionTest2 {

    private String oldVal = "hyde";
    private CollegeStudent studentOne;

    @BeforeEach
    public void beforeEach() {
        studentOne = new CollegeStudent();
        this.studentOne.setFirstname(oldVal);
        this.studentOne.setLastname("liao");
        this.studentOne.setEmailAddress("hydeliao@mail.com");
        this.studentOne.setStudentGrades(null);
    }

    @Test
    public void test() {

        String newVal = "new hyde";

        // Arrange
        ImmutableStudent immutableStudent = this.studentOne;
        String originalVal = immutableStudent.getFirstname();

        // Act
        ReflectionTestUtils.setField(immutableStudent, "firstname", newVal);

        // Assert
        Assertions.assertThat(originalVal).isEqualTo(this.oldVal);
        Assertions.assertThat(this.studentOne.getFirstname()).isEqualTo(newVal);
    }
}
