package codesnippet.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import codesnippet.spring.rest.model.StudentCriteria;
import codesnippet.spring.rest.model.StudentDto;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static Logger LOGGER = LogManager.getLogger();

    private List<StudentDto> studentDtos;

    @GetMapping("/students")
    public List<StudentDto> getStudents(StudentCriteria criteria) {
        List<StudentDto> rtn = new ArrayList<>(this.studentDtos);

        if(criteria.getFirstName() != null) {
            rtn = rtn.stream()
                    .filter(e -> e.getFirstName().equals(criteria.getFirstName())).collect(Collectors.toList());
        }

        if(criteria.getLastName() != null) {
            rtn = this.studentDtos.stream()
                    .filter(e -> e.getLastName().equals(criteria.getLastName())).collect(Collectors.toList());
        }

        return rtn;
    }

    @PostConstruct
    public void loadData() {
        this.studentDtos = new ArrayList<>();
        this.studentDtos.add(new StudentDto("Poornima", "Patel"));
        this.studentDtos.add(new StudentDto("Mario", "Rossi"));
        this.studentDtos.add(new StudentDto("Mary", "Smith"));
    }
}
