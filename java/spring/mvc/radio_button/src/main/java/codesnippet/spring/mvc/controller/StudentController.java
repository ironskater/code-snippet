package codesnippet.spring.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesnippet.spring.mvc.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController
{
    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student("default-first", "default-last", ""));
        return "student-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        LOGGER.info(student.toString());
        return "student-confirmation";
    }
}
