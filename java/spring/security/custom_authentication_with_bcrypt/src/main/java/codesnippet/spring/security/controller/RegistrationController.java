package codesnippet.spring.security.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesnippet.spring.security.model.UserRegisterInfo;
import codesnippet.spring.security.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {

        model.addAttribute("user", new UserRegisterInfo());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
        @Valid @ModelAttribute("user") UserRegisterInfo userRegisterInfo,
        BindingResult theBindingResult,
        Model model) {

        String userName = userRegisterInfo.getUserName();
        LOGGER.info("Processing registration form for: {}", userName);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        // check the database if user already exists
        UserRegisterInfo existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("user", new UserRegisterInfo());
            model.addAttribute("registrationError", "User name already exists.");

            LOGGER.warn("User name already exists.");
            return "registration-form";
        }
        // create user account
        userService.save(userRegisterInfo);
        LOGGER.info("Successfully created user: {}", userName);
        return "registration-confirmation";
    }
}
