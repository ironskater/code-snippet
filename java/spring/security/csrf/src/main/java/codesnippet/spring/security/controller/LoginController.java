package codesnippet.spring.security.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/custom-loginPage")
    public String customLoginPage() {
        LOGGER.info("============== to get login page");
        // return "plain-login";
        return "fancy-login";
    }
}
