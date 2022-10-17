package codesnippet.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codesnippet.test.exceptionhandling.StudentOrGradeErrorResponse;
import codesnippet.test.exceptionhandling.StudentOrGradeNotFoundException;
import codesnippet.test.models.CollegeStudent;
import codesnippet.test.models.Gradebook;
import codesnippet.test.models.GradebookCollegeStudent;
import codesnippet.test.service.StudentAndGradeService;

@RestController
public class GradebookController {

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private Gradebook gradebook;


    @GetMapping(value = "/")
    public List<GradebookCollegeStudent> getStudents() {
        gradebook = studentService.getGradebook();
        return gradebook.getStudents();
    }


    @GetMapping("/studentInformation/{id}")
    public GradebookCollegeStudent studentInformation(@PathVariable int id) {

        if (!studentService.checkIfStudentIsNull(id)) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        return studentService.studentInformation(id);
    }


    @PostMapping(value = "/")
    public List<GradebookCollegeStudent> createStudent(@RequestBody CollegeStudent student) {

        studentService.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());
        gradebook = studentService.getGradebook();
        return gradebook.getStudents();
    }


    @DeleteMapping("/student/{id}")
    public List<GradebookCollegeStudent> deleteStudent(@PathVariable int id) {

        if (!studentService.checkIfStudentIsNull(id)) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        studentService.deleteStudent(id);
        gradebook = studentService.getGradebook();
        return gradebook.getStudents();
    }


    @PostMapping(value = "/grades")
    public GradebookCollegeStudent createGrade(@RequestParam("grade") double grade,
                                               @RequestParam("gradeType") String gradeType,
                                               @RequestParam("studentId") int studentId) {

        if (!studentService.checkIfStudentIsNull(studentId)) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        boolean success = studentService.createGrade(grade, studentId, gradeType);

        if (!success) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        GradebookCollegeStudent studentEntity = studentService.studentInformation(studentId);

        if (studentEntity == null) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        return studentEntity;
    }

    @DeleteMapping("/grades/{id}/{gradeType}")
    public GradebookCollegeStudent deleteGrade(@PathVariable int id, @PathVariable String gradeType) {

        int studentId = studentService.deleteGrade(id, gradeType);

        if (studentId == 0) {
            throw new StudentOrGradeNotFoundException("Student or Grade was not found");
        }

        return studentService.studentInformation(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentOrGradeErrorResponse> handleException(StudentOrGradeNotFoundException exc) {

        StudentOrGradeErrorResponse error = new StudentOrGradeErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentOrGradeErrorResponse> handleException(Exception exc) {

        StudentOrGradeErrorResponse error = new StudentOrGradeErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
