package codesnippet.jpa.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import codesnippet.jpa.dao.EmployeeDAO;
import codesnippet.jpa.model.EmployeeDto;
import codesnippet.jpa.persistence.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    /**
     * we have 3 impl: employeeDAOJpaImpl or employeeDAOHibernateImpl, and
     * employeeDAOSpringDataJpaImpl
     *
     * @param employeeDAO
     */
    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAOSpringDataJpaImpl") EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = this.employeeDAO.findAll();
        return employeeList.stream().map(
                e -> new EmployeeDto(e.getId(), e.getFirstName(), e.getLastName(), e.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDto findById(int id) {

        Employee employee = this.employeeDAO.findById(id).orElse(null);

        return employee == null ? null
                : new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
                        employee.getEmail());
    }

    @Override
    @Transactional
    public void save(EmployeeDto employeeDto) {
        this.employeeDAO.save(new Employee(employeeDto.getFirstName(), employeeDto.getLastName(),
                employeeDto.getEmail()));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.employeeDAO.deleteById(id);
    }
}
