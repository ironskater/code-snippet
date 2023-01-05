package codesnippet.spring.mybatis.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import codesnippet.spring.mybatis.persistence.po.UserInfoPo;

public interface UserInfoMapper {

    int save(UserInfoPo userInfoPo);

    int saveAll(@Param("userInfoPos") List<UserInfoPo> userInfoPos);

    int deleteById(long id);

    UserInfoPo findById(@Param("id") long id);

    List<UserInfoPo> findAll();
}
