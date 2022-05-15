package codesnippet.spring.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import codesnippet.spring.mvc.model.Student;

@Controller
@RequestMapping("/student")
@ConfigurationProperties
@PropertySource({"classpath:countries.properties"})
public class StudentController
{
	// How to fill HashMap from java property file with Spring @Value
	// https://stackoverflow.com/questions/28369458/how-to-fill-hashmap-from-java-property-file-with-spring-value
	@Value("#{${countryByOptions}}")
	private Map<String, String> countryByOptions;

	@RequestMapping("/showForm")
	public String
		showForm(Model model)
	{
		Student student = new Student();

		// add the student attribute to the model
		// so that the student-form jsp can use it
		model.addAttribute("student", student);
		model.addAttribute("countryByOptions", countryByOptions);

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
