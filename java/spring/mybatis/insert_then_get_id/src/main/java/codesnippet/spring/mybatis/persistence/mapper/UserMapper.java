package codesnippet.spring.mybatis.persistence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import codesnippet.spring.mybatis.persistence.po.UserPo;

@Mapper
public interface UserMapper {

    UserPo findById(@Param("id") long id);

    int save(UserPo userPo);

    int batchInsert(@Param("userPos") List<UserPo> userPos);

    int batchInsertByMap(@Param("userPosByUsername") Map<String, UserPo> userPosByUsername);

    int batchInsertByEntrySet(@Param("pwByUsername") Map<String, String> pwByUsername);

    int batchUpdate(@Param("updatedUserPos") List<UserPo> userPos);
}
