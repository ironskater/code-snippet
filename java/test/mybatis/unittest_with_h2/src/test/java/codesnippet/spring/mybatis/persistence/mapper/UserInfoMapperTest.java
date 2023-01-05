package codesnippet.spring.mybatis.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import codesnippet.spring.mybatis.persistence.po.UserInfoPo;

@AutoConfigureMybatis
@ExtendWith(SpringExtension.class)
@MapperScan("codesnippet.spring.mybatis.persistence.mapper")
@TestPropertySource("/application-test.properties")
@Transactional
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    @Sql("/db/schema.sql")
    @Sql("/db/data.sql")
    public void deleteById() {

        List<UserInfoPo> beforeDeletion = userInfoMapper.findAll();

        int deletedCount = userInfoMapper.deleteById(1L);

        List<UserInfoPo> aftereDeletion = userInfoMapper.findAll();

        Assertions.assertThat(beforeDeletion).size().isEqualTo(5);
        Assertions.assertThat(aftereDeletion).size().isEqualTo(4);
        Assertions.assertThat(deletedCount).isEqualTo(1);
    }

    @Test
    @Sql("/db/schema.sql")
    @Sql("/db/data.sql")
    public void findById() {

        List<UserInfoPo> userInfoDOs = userInfoMapper.findAll();

        UserInfoPo userInfoDo = userInfoMapper.findById(1L);

        Assertions.assertThat(userInfoDo).isNotNull();
        Assertions.assertThat(userInfoDOs).size().isEqualTo(5);
    }

    @Test
    @Sql("/db/schema.sql")
    @Sql("/db/data.sql")
    public void save() {

        UserInfoPo insertPo = new UserInfoPo(null, "just test", 5566);
        int insertCount = userInfoMapper.save(insertPo);
        UserInfoPo userInfoPo = userInfoMapper.findById(insertPo.getId());

        Assertions.assertThat(insertCount).isEqualTo(1);
        Assertions.assertThat(userInfoPo).isNotNull();
        Assertions.assertThat(userInfoPo).usingRecursiveComparison().isEqualTo(insertPo);
    }

    @Test
    @Sql("/db/schema.sql")
    @Sql("/db/data.sql")
    public void saveAll() {

        UserInfoPo insertPo1 = new UserInfoPo(null, "just test1", 5566);
        UserInfoPo insertPo2 = new UserInfoPo(null, "just test2", 7788);

        List<UserInfoPo> insertList = new ArrayList<>();
        insertList.add(insertPo1);
        insertList.add(insertPo2);

        int insertCount = userInfoMapper.saveAll(insertList);
        UserInfoPo userInfoPo1 = userInfoMapper.findById(insertList.get(0).getId());
        UserInfoPo userInfoPo2 = userInfoMapper.findById(insertList.get(1).getId());

        Assertions.assertThat(insertCount).isEqualTo(2);
        Assertions.assertThat(insertList).usingRecursiveFieldByFieldElementComparator()
            .contains(userInfoPo1, userInfoPo2);
    }
}
