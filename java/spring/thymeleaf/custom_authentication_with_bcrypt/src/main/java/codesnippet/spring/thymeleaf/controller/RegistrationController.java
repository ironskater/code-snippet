package codesnippet.spring.thymeleaf.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
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

import codesnippet.spring.thymeleaf.model.UserRegisterInfo;
import codesnippet.spring.thymeleaf.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    private Map<String, String> roles;

    @PostConstruct
    protected void loadRoles() {
        // using hashmap, could also read this info from a database
        roles = new LinkedHashMap<>();
        // key=the role, value=display to user
        roles.put("ROLE_EMPLOYEE", "Employee");
        roles.put("ROLE_MANAGER", "Manager");
        roles.put("ROLE_ADMIN", "Admin");
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {

        model.addAttribute("user", new UserRegisterInfo());
        model.addAttribute("roles", this.roles);

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
            model.addAttribute("user", new UserRegisterInfo());
            model.addAttribute("roles", this.roles);
            model.addAttribute("registrationError", "User name/password can not be empty.");
            LOGGER.warn("User name/password can not be empty.");
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
