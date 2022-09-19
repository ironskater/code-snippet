package codesnippet.jpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import codesnippet.jpa.model.EmployeeDto;
import codesnippet.jpa.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> findAll() {
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto findById(@PathVariable("id") int id) {
        EmployeeDto employeeDto = this.employeeService.findById(id);

        if (employeeDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no such resource");
        }

        return employeeDto;
    }

    @PostMapping("/employees")
    public void save(@RequestBody EmployeeDto employeeDto) {
        this.employeeService.save(employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable("id") int id) {
        this.employeeService.deleteById(id);
    }
}
