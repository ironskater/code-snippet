package codesnippet.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController
{
	// To show the initial HTML form
	@RequestMapping("/showForm")
	public String
		showForm()
	{
		return "helloworld-form";
	}

	// Process the HTML form
	@RequestMapping("/processFormWithName")
	public String
		processForm(@RequestParam("studentName") String name,
					Model model)
	{
		model.addAttribute(	"message",
							"hello world!! it\'s the student name: " + name);

		return "helloworld";
	}
}