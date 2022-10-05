package codesnippet.spring.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import codesnippet.spring.security.model.UserRegisterInfo;

public interface UserService extends UserDetailsService {
    public UserRegisterInfo findByUserName(String userName);
    public void save(UserRegisterInfo info);
}
