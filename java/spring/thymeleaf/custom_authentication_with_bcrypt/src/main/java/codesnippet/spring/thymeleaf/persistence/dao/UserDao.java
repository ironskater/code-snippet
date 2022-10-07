package codesnippet.spring.thymeleaf.persistence.dao;

import codesnippet.spring.thymeleaf.persistence.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);
}
