package codesnippet.spring.security.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codesnippet.spring.security.persistence.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUserName(String userName) {
        // get current hibernate session
        Session session = this.sessionFactory.getCurrentSession();

        Query<User> query = session.createNativeQuery("SELECT * FROM users WHERE username = :uName", User.class);
        query.setParameter("uName", userName);

        User rtn = null;

        try {
            rtn = query.getSingleResult();
        } catch(Exception ex) {

        }

        return rtn;
    }

    @Override
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
