package codesnippet.jpa.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import codesnippet.jpa.persistence.Employee;

@Repository
public class EmployeeDAOSpringDataJpaImpl implements EmployeeDAO {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDAOSpringDataJpaImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }
}
