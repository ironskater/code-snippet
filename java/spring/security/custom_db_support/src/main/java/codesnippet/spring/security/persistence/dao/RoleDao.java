package codesnippet.spring.security.persistence.dao;

import codesnippet.spring.security.persistence.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String roleName);
}
