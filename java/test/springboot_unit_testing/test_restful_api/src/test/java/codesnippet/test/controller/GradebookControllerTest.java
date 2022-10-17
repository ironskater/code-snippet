package codesnippet.test.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import codesnippet.test.models.CollegeStudent;
import codesnippet.test.repository.HistoryGradesDao;
import codesnippet.test.repository.MathGradesDao;
import codesnippet.test.repository.ScienceGradesDao;
import codesnippet.test.repository.StudentDao;
import codesnippet.test.service.StudentAndGradeService;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class GradebookControllerTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Mock
    private StudentAndGradeService studentCreateServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradesDao mathGradeDao;

    @Autowired
    private ScienceGradesDao scienceGradeDao;

    @Autowired
    private HistoryGradesDao historyGradeDao;

    @Autowired
    private StudentAndGradeService studentService;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @Autowired
    private CollegeStudent student;

    @BeforeAll
    public static void setup() {

        request = new MockHttpServletRequest();
        request.setParameter("firstname", "hyde");
        request.setParameter("lastname", "liao");
        request.setParameter("emailAddress", "hydeliao@email.com");
    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }

    @Test
    @Order(1)
    public void create_valid_grade() throws Exception {

        List<CollegeStudent> collegeStudents = this.studentDao.findAll();

        assertThat(collegeStudents).size().isEqualTo(1);

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/grades")
                .contentType(MediaType.APPLICATION_JSON)
                .param("grade", "85.00")
                .param("gradeType", "math")
                .param("studentId", String.valueOf(collegeStudents.get(0).getId()))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(collegeStudents.get(0).getId())))
        .andExpect(jsonPath("$.firstname", is("defaultHyde")))
        .andExpect(jsonPath("$.lastname", is("defaultLiao")))
        .andExpect(jsonPath("$.emailAddress", is("default@email.com")))
        .andExpect(jsonPath("$.studentGrades.mathGradeResults", hasSize(2)));
    }

    @Test
    public void getStudents() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void createStudent() throws Exception {

        this.student.setFirstname("first");
        this.student.setLastname("last");
        this.student.setEmailAddress("email");

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

        CollegeStudent collegeStudent = this.studentDao.findByEmailAddress("email");
        assertThat(collegeStudent).isNotNull();
    }

    @Test
    public void deleteStudent() throws Exception {

        List<CollegeStudent> collegeStudents = this.studentDao.findAll();

        assertThat(collegeStudents).size().isEqualTo(1);

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/student/{id}", collegeStudents.get(0).getId())
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(0)));

        assertThat(this.studentDao.findAll()).size().isEqualTo(0);
    }

    @Test
    public void delete_non_exist_student_return_status404() throws Exception {

        assertThat(this.studentDao.findById(9527)).isNotPresent();

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/student/{id}", 9527)
        )
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status", is(404)))
        .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void succeed_to_get_the_student_information() throws Exception {

        List<CollegeStudent> collegeStudents = this.studentDao.findAll();
        assertThat(collegeStudents).size().isEqualTo(1);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/studentInformation/{id}", collegeStudents.get(0).getId())
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(collegeStudents.get(0).getId())))
        .andExpect(jsonPath("$.firstname", is("defaultHyde")))
        .andExpect(jsonPath("$.lastname", is("defaultLiao")))
        .andExpect(jsonPath("$.emailAddress", is("default@email.com")));
    }

    @Test
    public void to_get_the_non_existed_student_information_returns_status404() throws Exception {

        assertThat(this.studentDao.findById(9527)).isNotPresent();

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/studentInformation/{id}", 9527)
        )
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", is(404)))
        .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }
}
