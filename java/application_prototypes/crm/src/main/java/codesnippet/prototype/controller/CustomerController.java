package codesnippet.prototype.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codesnippet.prototype.persistence.entity.Customer;
import codesnippet.prototype.service.CustomerService;
import codesnippet.prototype.utils.SortUtils;

@Controller
@RequestMapping("/customers")
public class CustomerController
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model, @RequestParam(name = "sort", required = false) String sort) {
        // get customers from the service
		List<Customer> theCustomers = null;

		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
		}
		else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}

		// add the customers to the model
		model.addAttribute("customers", theCustomers);

		return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
        Customer customer = this.customerService.getCustomer(customerId);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int customerId) {
        this.customerService.deleteCustomer(customerId);
        return "redirect:/customers/list";
    }

    @GetMapping("/search")
    public String searchCustomers(  @RequestParam("theSearchName") String theSearchName,
                                    Model model) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
