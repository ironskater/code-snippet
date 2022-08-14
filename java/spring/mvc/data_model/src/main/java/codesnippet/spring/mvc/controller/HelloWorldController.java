package codesnippet.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController
{
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @GetMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // Using HttpServletRequest to get req parameter

    // @GetMapping("/upperCase")
    // public String upperCase(HttpServletRequest req, Model model) {
    //     String name = req.getParameter("studentName");
    //     model.addAttribute("message", "hello!! " + name.toUpperCase());
    //     return "helloworld";
    // }

    @GetMapping("/upperCase")
    public String upperCase(@RequestParam("studentName") String studentName, Model model) {
        model.addAttribute("message", "hello!! " + studentName.toUpperCase());
        return "helloworld";
    }
}
