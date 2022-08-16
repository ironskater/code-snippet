package codesnippet.spring.mvc.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesnippet.spring.mvc.model.Student;

@Controller
@RequestMapping("/student")
@PropertySource("classpath:bloodTypes.properties")
public class StudentController
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Value("#{${valueByKey}}")
    private Map<String, String> bloodTypeOptions;

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student("default-first", "default-last", "", ""));
        model.addAttribute("bloodTypeOptions", this.bloodTypeOptions);
        return "student-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        LOGGER.info(student.toString());
        return "student-confirmation";
    }
}
