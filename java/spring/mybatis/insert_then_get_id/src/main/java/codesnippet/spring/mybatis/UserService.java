package codesnippet.spring.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import codesnippet.spring.mybatis.persistence.mapper.UserMapper;
import codesnippet.spring.mybatis.persistence.po.UserPo;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserPo findById(long id) {
        return userMapper.findById(id);
    }
}
