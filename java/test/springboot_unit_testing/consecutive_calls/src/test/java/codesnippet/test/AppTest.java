package codesnippet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @DisplayName("Throw runtime error")
    @Test

    public void throwRuntimeError() {
        CollegeStudent nullStudent = null;

        doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);
        assertThrows(RuntimeException.class, () -> {
            applicationServiceImpl.checkNull(null);
        });

        verify(this.applicationDao, times(1)).checkNull(nullStudent);
    }

    @DisplayName("consecutive calls")
    @Test
    public void consecutiveCalls() {

        CollegeStudent nullStudent = null;

        when(applicationDao.checkNull(nullStudent)).thenThrow(new RuntimeException()).thenReturn(
            "Do not throw exception second time");

        // first call: throws exception
        assertThrows(RuntimeException.class, () -> {
            applicationServiceImpl.checkNull(null);
        });

        // second call: just return a string
        assertEquals("Do not throw exception second time", applicationServiceImpl.checkNull(nullStudent));

        verify(applicationDao, times(2)).checkNull(nullStudent);
    }
}
