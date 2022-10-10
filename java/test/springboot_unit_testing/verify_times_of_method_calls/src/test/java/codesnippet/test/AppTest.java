package codesnippet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import codesnippet.test.dao.ApplicationDao;
import codesnippet.test.model.CollegeStudent;
import codesnippet.test.model.StudentGrades;
import codesnippet.test.service.ApplicationServiceImpl;

@SpringBootTest
public class AppTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CollegeStudent studentOne;

    @Autowired
    private StudentGrades studentGrades;

    // Create mock for Dao
    @Mock
    private ApplicationDao applicationDao;

    // Inject mock dependencies
    @InjectMocks
    private ApplicationServiceImpl applicationServiceImpl;

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
        verify(applicationDao, times(2)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }
}
