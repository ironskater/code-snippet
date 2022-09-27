package codesnippet.spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import codesnippet.spring.mvc.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees;

    @PostConstruct
    private void loadData() {

        // create employees
        Employee emp1 = new Employee(1, "Leslie", "Andrews", "leslie@luv2code.com");
        Employee emp2 = new Employee(2, "Emma", "Baumgarten", "emma@luv2code.com");
        Employee emp3 = new Employee(3, "Avani", "Gupta", "avani@luv2code.com");

        // create the list
        this.employees = new ArrayList<>();

        // add to the list
        this.employees.add(emp1);
        this.employees.add(emp2);
        this.employees.add(emp3);
    }

    @GetMapping
    public String getEmployees(Model model) {

        model.addAttribute("employees", this.employees);
        return "employees-page";
    }
}
