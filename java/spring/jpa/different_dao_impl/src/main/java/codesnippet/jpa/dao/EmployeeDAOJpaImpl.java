package codesnippet.jpa.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import codesnippet.jpa.persistence.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Query query = entityManager.createQuery("from Employee", Employee.class);

        return query.getResultList();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(this.entityManager.find(Employee.class, id));
    }

    @Override
    public void save(Employee employee) {
        Employee rtn = entityManager.merge(employee);

        employee.setId(rtn.getId());
    }

    @Override
    public void deleteById(int id) {

        Query query = this.entityManager.createQuery("delete from Employee where id =: employeeId");

        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
