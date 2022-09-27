package codesnippet.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateTimeController {

    @GetMapping("/hello")
    public String getDateTime(Model model) {

        model.addAttribute("date", new java.util.Date());

        return "helloworld"; // refer to src/main/resources/templates/helloworld.html
    }
}
