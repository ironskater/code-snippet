package codesnippet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import codesnippet.test.dao.ApplicationDao;
import codesnippet.test.model.CollegeStudent;
import codesnippet.test.model.StudentGrades;
import codesnippet.test.service.ApplicationService;

@SpringBootTest
public class AppTest {

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

        studentOne.setFirstname("hyde");
        studentOne.setLastname("liao");
        studentOne.setEmailAddress("hydeliao@spring.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("when & verify")
    @Test
    public void assertEqualTestAddGrades() {

        // when method addGradeResultsForSingleClass(...) is called then return 100.0
        when(this.applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.0);

        assertEquals(100, this.applicationServiceImpl.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()));

        // verify is used to verify the DAO method was called
        verify(applicationDao, times(1)).addGradeResultsForSingleClass(this.studentOne.getStudentGrades().getMathGradeResults());
    }

    @DisplayName("Find GPA")
    @Test
    public void assertEqualsTestFindGpa() {

        when(this.applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(88.31);
        assertEquals(88.31, this.applicationServiceImpl.findGradePointAverage(this.studentOne.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Not Null")
    @Test
    public void assertNotNull() {

        when(this.applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);
        assertEquals(true, this.applicationServiceImpl.checkNull(this.studentOne.getStudentGrades().getMathGradeResults()));
    }
}
