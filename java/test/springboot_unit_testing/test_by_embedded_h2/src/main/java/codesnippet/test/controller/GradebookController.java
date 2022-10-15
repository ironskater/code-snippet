package codesnippet.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import codesnippet.test.model.Gradebook;
import codesnippet.test.persistence.entity.student.CollegeStudent;
import codesnippet.test.service.StudentAndGradeService;

@Controller
public class GradebookController {

    @Autowired
    private Gradebook gradebook;

    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @GetMapping(value = "/")
    public String getStudents(Model model) {

        List<CollegeStudent> collegeStudents = this.studentAndGradeService.getGradebook();
        model.addAttribute("students", collegeStudents);

        return "index";
    }

    @PostMapping(value = "/")
    public String createStudent(@ModelAttribute("student") CollegeStudent collegeStudent, Model model) {

        this.studentAndGradeService.createStudent(collegeStudent.getFirstname(), collegeStudent.getLastname(), collegeStudent.getEmailAddress());
        List<CollegeStudent> collegeStudents = this.studentAndGradeService.getGradebook();
        model.addAttribute("students", collegeStudents);

        return "index";
    }

    @GetMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {

        if(this.studentAndGradeService.isStudentNull(id)) {
            return "error";
        }

        this.studentAndGradeService.deleteStudent(id);
        List<CollegeStudent> collegeStudents = this.studentAndGradeService.getGradebook();
        model.addAttribute("students", collegeStudents);

        return "index";
    }

    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
        return "studentInformation";
    }
}
