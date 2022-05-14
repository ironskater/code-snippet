package codesnippet.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import codesnippet.spring.mvc.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController
{
	@RequestMapping("/showForm")
	public String
		showForm(Model model)
	{
		Student student = new Student();

		// add the student attribute to the model
		// so that the student-form jsp can use it
		model.addAttribute("student", student);

		return "student-form";
	}

	@RequestMapping("processForm")
	public String
		processForm(@ModelAttribute("student") Student student)
	{
		System.out.println("the student: " + student.getFirstName() + " " + student.getLastName());
		return "student-confirmation";
	}
}
