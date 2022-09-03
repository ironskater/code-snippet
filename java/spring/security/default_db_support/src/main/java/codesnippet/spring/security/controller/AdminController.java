package codesnippet.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }
}
