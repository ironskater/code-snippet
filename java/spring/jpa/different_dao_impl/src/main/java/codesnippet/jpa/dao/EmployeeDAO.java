package codesnippet.jpa.dao;

import java.util.List;
import java.util.Optional;
import codesnippet.jpa.persistence.Employee;

public interface EmployeeDAO {

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
