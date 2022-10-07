package codesnippet.spring.thymeleaf.persistence.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codesnippet.spring.thymeleaf.persistence.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = LogManager.getLogger();

    private SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findRoleByName(String roleName) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Role> query = session.createNativeQuery("SELECT * FROM roles WHERE name = :roleName", Role.class);
        query.setParameter("roleName", roleName);

        Role rtn = null;

        try {
            rtn = query.getSingleResult();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return rtn;
    }
}
