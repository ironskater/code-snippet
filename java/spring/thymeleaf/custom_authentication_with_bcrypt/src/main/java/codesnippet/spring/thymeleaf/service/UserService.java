package codesnippet.spring.thymeleaf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import codesnippet.spring.thymeleaf.model.UserRegisterInfo;

public interface UserService extends UserDetailsService {

    public UserRegisterInfo findByUserName(String userName);

    public void save(UserRegisterInfo info);
}
