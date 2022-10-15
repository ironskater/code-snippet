package codesnippet.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import codesnippet.test.persistence.dao.HistoryGradeDao;
import codesnippet.test.persistence.dao.MathGradeDao;
import codesnippet.test.persistence.dao.ScienceGradeDao;
import codesnippet.test.persistence.dao.StudentDao;
import codesnippet.test.persistence.entity.grade.HistoryGrade;
import codesnippet.test.persistence.entity.grade.MathGrade;
import codesnippet.test.persistence.entity.grade.ScienceGrade;
import codesnippet.test.persistence.entity.student.CollegeStudent;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void afterEach() {
        this.jdbcTemplate.execute("TRUNCATE TABLE student");
    }

    @Test
    public void createStudent_shouldSucceed() {

        // Arrange
        String email = "hydeliao@email.com";

        // Act
        this.studentAndGradeService.createStudent("hyde", "liao", email);

        // Assert
        CollegeStudent actual = studentDao.findByEmailAddress(email).orElseGet(() -> new CollegeStudent());
        assertThat(actual).usingRecursiveComparison().ignoringFields("id")
            .isEqualTo(new CollegeStudent("hyde", "liao", email));
    }

    @Test
    public void isStudentNull_shouldSucceed() {

        // Arrange
        CollegeStudent expected = studentDao.save(new CollegeStudent("hyde", "liao", "hydeliao@email.com"));

        // Act, Assert
        assertThat(studentAndGradeService.isStudentNull(expected.getId() + 1)).isTrue();
        assertThat(studentAndGradeService.isStudentNull(expected.getId())).isFalse();
    }

    @Test
    public void deleteStudent_shouldSucceed() {

        // Arrange
        CollegeStudent collegeStudent = studentDao.save(new CollegeStudent("hyde", "liao", "hydeliao@email.com"));

        // Act
        boolean isStudentNullBeforeDelete = this.studentAndGradeService.isStudentNull(collegeStudent.getId());
        this.studentAndGradeService.deleteStudent(collegeStudent.getId());
        boolean isStudentNullAfterDelete = this.studentAndGradeService.isStudentNull(collegeStudent.getId());

        // Assert
        assertThat(isStudentNullBeforeDelete).isFalse();
        assertThat(isStudentNullAfterDelete).isTrue();
    }

    @Sql("/insertStudents.sql")
    @Test
    public void getGradebook_shouldSucceed() {

        List<CollegeStudent> collegeStudents = this.studentAndGradeService.getGradebook();

        assertThat(collegeStudents).size().isEqualTo(5);
    }

    @Test
    public void create_student_math_grade_is_successful() {

        CollegeStudent collegeStudent = studentDao.save(new CollegeStudent("hyde", "liao", "hydeliao@email.com"));
        double grade = 66.0;

        this.studentAndGradeService.createGrade(grade, collegeStudent.getId(), "math");
        List<MathGrade> mathGrades = this.mathGradeDao.findGradesByStudentId(collegeStudent.getId());

        assertThat(mathGrades).extracting(MathGrade::getStudentId, MathGrade::getGrade)
            .containsExactly(tuple(collegeStudent.getId(), grade));
    }

    @Test
    public void create_student_science_grade_is_successful() {

        CollegeStudent collegeStudent = studentDao.save(new CollegeStudent("hyde", "liao", "hydeliao@email.com"));
        double grade = 66.0;

        this.studentAndGradeService.createGrade(grade, collegeStudent.getId(), "science");
        List<ScienceGrade> scienceGrades = this.scienceGradeDao.findGradesByStudentId(collegeStudent.getId());

        assertThat(scienceGrades).extracting(ScienceGrade::getStudentId, ScienceGrade::getGrade)
            .containsExactly(tuple(collegeStudent.getId(), grade));
    }

    @Test
    public void create_student_history_grade_is_successful() {

        CollegeStudent collegeStudent = studentDao.save(new CollegeStudent("hyde", "liao", "hydeliao@email.com"));
        double grade = 66.0;

        this.studentAndGradeService.createGrade(grade, collegeStudent.getId(), "history");
        List<HistoryGrade> historyGrades = this.historyGradeDao.findGradesByStudentId(collegeStudent.getId());

        assertThat(historyGrades).extracting(HistoryGrade::getStudentId, HistoryGrade::getGrade)
            .containsExactly(tuple(collegeStudent.getId(), grade));
    }
}
