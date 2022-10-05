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

    // here we add support to trim empty string to null, leading/tailing spaces
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationPage(Model model) {
        model.addAttribute("crmUser", new UserRegisterInfo());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(  @Valid @ModelAttribute("crmUser") UserRegisterInfo info,
                                            BindingResult bindingResult,
                                            Model model) {

        LOGGER.info("Processing registration form for:\n", info.toString());

        // if user enter any invalid data, back to the registration-form
        // UserRegisterInfo has validation annotations to check for empty fields,
        // password and matching password matcher, email validations.
        if(bindingResult.hasErrors()) {
            return "registratioin-form";
        }

        if(isUserAlreadyExisted(info.getUserName())) {
            model.addAttribute("crmUser", new UserRegisterInfo());
            model.addAttribute("registrationError", "User name already exists");

            LOGGER.warn("User already exists, username[{}]", info.getUserName());
            return "registratioin-form";
        }

        this.userService.save(info);

        LOGGER.info("Successfully created user: ", info.getUserName());

        return "registration-confirmation";
    }

    private boolean isUserAlreadyExisted(String username) {
        UserRegisterInfo info = this.userService.findByUserName(username);
        return info != null;
    }
}
