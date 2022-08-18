package codesnippet.spring.validation.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/")
    public String home() {
        String jspFile = "main-menu";
        LOGGER.info("================ Redirect to {}", jspFile);
        return jspFile;
    }
}
