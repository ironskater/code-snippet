package codesnippet.spring.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codesnippet.spring.security.model.UserRegisterInfo;
import codesnippet.spring.security.persistence.dao.RoleDao;
import codesnippet.spring.security.persistence.dao.UserDao;
import codesnippet.spring.security.persistence.entity.Role;
import codesnippet.spring.security.persistence.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl( UserDao userDao,
                            RoleDao roleDao,
                            PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserRegisterInfo findByUserName(String userName) {

        User user = this.userDao.findByUserName(userName);

        if(user == null) {
            return null;
        }

        return new UserRegisterInfo(user.getUserName(),
                                    user.getPassword(),
                                    null,
                                    user.getFirstName(),
                                    user.getLastName(),
                                    user.getEmail());
    }

    @Transactional
    @Override
    public void save(UserRegisterInfo info) {
        User user = new User();
        user.setUserName(info.getUserName());
        // make sure that we need to encode the password before saving the user.
        user.setPassword(this.passwordEncoder.encode(info.getPassword()));
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        user.setEmail(info.getEmail());

        // give user default role of "employee"
        user.setRoles(Arrays.asList(this.roleDao.findRoleByName("ROLE_EMPLOYEE")));

        this.userDao.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUserName(username);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(  user.getUserName(),
                                                                        user.getPassword(),
                                                                        this.mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
