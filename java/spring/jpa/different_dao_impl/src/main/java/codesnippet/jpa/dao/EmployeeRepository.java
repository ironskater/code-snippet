package codesnippet.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import codesnippet.jpa.persistence.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
