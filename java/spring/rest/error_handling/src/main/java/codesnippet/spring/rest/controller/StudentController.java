package codesnippet.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import codesnippet.spring.rest.exception.StudentNotFoundException;
import codesnippet.spring.rest.model.StudentDto;
import codesnippet.spring.rest.model.StudentErrorResponse;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static final Logger LOGGER = LogManager.getLogger();
    private List<StudentDto> studentDtos;

    @GetMapping("/students")
    public List<StudentDto> getStudents() {
        return this.studentDtos;
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable("studentId") int studentId) {

        if ((studentId >= this.studentDtos.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        StudentDto rtn = this.studentDtos.get(studentId);

        if (rtn == null) {
            return new ResponseEntity<String>("resource not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudentDto>(rtn, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @PostConstruct
    public void loadData() {
        this.studentDtos = new ArrayList<>();
        this.studentDtos.add(new StudentDto("Poornima", "Patel"));
        this.studentDtos.add(new StudentDto("Mario", "Rossi"));
        this.studentDtos.add(new StudentDto("Mary", "Smith"));
        this.studentDtos.add(null);
    }
}
