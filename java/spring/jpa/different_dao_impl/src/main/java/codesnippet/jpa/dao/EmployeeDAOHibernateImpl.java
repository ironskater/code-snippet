package codesnippet.jpa.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import codesnippet.jpa.persistence.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Session session = this.entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Optional<Employee> findById(int id) {

        Session session = this.entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Employee.class, id));
    }

    @Override
    public void save(Employee employee) {

        Session session = this.entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {

        Session session = this.entityManager.unwrap(Session.class);

        // 不用加泛型，否則會有以下例外
        // java.lang.IllegalArgumentException: Update/delete queries cannot be typed
        Query query = session.createQuery("delete from Employee where id = : employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
