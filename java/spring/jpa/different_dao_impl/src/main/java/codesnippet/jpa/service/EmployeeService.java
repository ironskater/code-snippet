package codesnippet.jpa.service;

import java.util.List;
import codesnippet.jpa.model.EmployeeDto;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto findById(int id);

    void save(EmployeeDto employeeDto);

    void deleteById(int id);
}
