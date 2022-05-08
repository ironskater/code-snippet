package codesnippet.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/processForm")
	public String
		processForm()
	{
		return "helloworld";
	}

	// read the form data and add data to the model
	@RequestMapping("/processFormUpperCase")
	public String
		letsShoutDude(	HttpServletRequest request,
						Model model)
	{
		String theName = request.getParameter("studentName");

		theName = theName.toUpperCase();

		String result = "Yo! " + theName;

		model.addAttribute(	"message",
							result);

		return "helloworld";
	}
}