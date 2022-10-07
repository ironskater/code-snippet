package codesnippet.spring.thymeleaf.persistence.dao;

import codesnippet.spring.thymeleaf.persistence.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String roleName);
}
