package codesnippet.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
