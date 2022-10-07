package codesnippet.spring.thymeleaf.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/")
    public String home() {
        LOGGER.info("redirect to home .................................");
        return "home";
    }
}
