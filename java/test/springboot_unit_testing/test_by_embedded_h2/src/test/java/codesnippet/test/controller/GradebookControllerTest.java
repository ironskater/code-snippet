package codesnippet.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import codesnippet.test.persistence.dao.StudentDao;
import codesnippet.test.persistence.entity.student.CollegeStudent;
import codesnippet.test.service.StudentAndGradeService;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {

    private static MockHttpServletRequest mockHttpServletRequest;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentAndGradeService studentAndGradeServiceMock;

    @Autowired
    private StudentDao studentDao;

    @BeforeAll
    public static void setupRequest() {

        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setParameter("firstname", "hyde");
        mockHttpServletRequest.setParameter("lastname", "liao");
        mockHttpServletRequest.setParameter("email", "hyde@email.com");
    }

    @Test
    public void getStudentsHttpRequest() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON)
            // .param("firstname", mockHttpServletRequest.getParameterValues("firstname"))
            // .param("lastname", mockHttpServletRequest.getParameterValues("lastname"))
            // .param("emailAddress", mockHttpServletRequest.getParameterValues("email"))
            .param("firstname", "hyde")
            .param("lastname", "liao")
            .param("emailAddress", "hyde@email.com")
        ).andExpect(status().isOk()).andReturn();

        // Asseret
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        Optional<CollegeStudent> collegeStudent = this.studentDao.findByEmailAddress("hyde@email.com");

        assertThat(collegeStudent).isPresent();
        assertThat(collegeStudent.get()).usingRecursiveComparison().ignoringFields("id")
            .isEqualTo(new CollegeStudent("hyde", "liao", "hyde@email.com"));
    }

    @Test
    public void deleteStudentHttpRequest() throws Exception {

        CollegeStudent collegeStudent = this.studentDao.save(new CollegeStudent("hyde", "liao", "hyde@email.com"));
        assertThat(this.studentDao.existsById(collegeStudent.getId())).isTrue();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", collegeStudent.getId())).andExpect(status().isOk())
            .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        assertThat(this.studentDao.existsById(collegeStudent.getId())).isFalse();
    }

    @Test
    public void deleteStudentHttpRequestErrorPage() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", 9527)).andExpect(status().isOk())
            .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "error");
    }
}
