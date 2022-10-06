package codesnippet.spring.security.persistence.dao;

import codesnippet.spring.security.persistence.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    void save(User user);
}
