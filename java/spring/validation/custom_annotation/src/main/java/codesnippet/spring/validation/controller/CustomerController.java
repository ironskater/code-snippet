package codesnippet.spring.validation.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import codesnippet.spring.validation.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController
{
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 1. trim leading- and trailing-whitespaces
     * 2. if a string only has whitespaces, trims it to null
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    /**
     *
     * @param customer: Use @Valid to tell spring to perfofrm validation rules on the customer object
     * @param bindingResult: which stores the result of the validation
     * @return
     */
    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult) {
        LOGGER.info(customer.toString());

        // use this logger to check error code
        LOGGER.info("Binding Result: " + bindingResult + System.lineSeparator());

        if(bindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
