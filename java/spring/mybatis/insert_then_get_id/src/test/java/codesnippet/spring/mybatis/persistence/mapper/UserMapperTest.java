package codesnippet.spring.mybatis.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import codesnippet.spring.mybatis.TestTransactionConfig;
import codesnippet.spring.mybatis.UserService;
import codesnippet.spring.mybatis.persistence.po.UserPo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    DataSourceAutoConfiguration.class,
    MybatisAutoConfiguration.class,
    TestTransactionConfig.class,
    UserService.class
})
@MapperScan("codesnippet.spring.mybatis.persistence.mapper")
@TestPropertySource("/application-test.properties")
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    @Sql("/db/schema.sql")
    // @Rollback(false)
    public void testFindById() {

        UserPo expected = new UserPo(null, "username", "password");

        int count = this.userMapper.save(expected);

        UserPo actual = this.userService.findById(expected.getId());

        // Assert
        Assertions.assertThat(count).isEqualTo(1);

        Assertions.assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }

    @Test
    @Sql("/db/schema.sql")
    public void testBatchInsert_insertDataNormally_thenGetIncrementId() {

        UserPo userPo1 = new UserPo(null, "user1", "pw1");
        UserPo userPo2 = new UserPo(null, "user2", "pw2");
        UserPo userPo3 = new UserPo(null, "user3", "pw3");

        List<UserPo> userPos = new ArrayList<>();
        userPos.add(userPo1);
        userPos.add(userPo2);
        userPos.add(userPo3);

        int count = this.userMapper.batchInsert(userPos);

        // Assert
        Assertions.assertThat(count).isEqualTo(userPos.size());

        userPos.forEach(e ->
            Assertions.assertThat(e.getId()).isNotNull());
    }

    @Test
    @Sql("/db/schema.sql")
    public void testBatchInsertByMap_insertDataNormally_thenGetIncrementId() {

        UserPo userPo1 = new UserPo(null, "user1", "pw1");
        UserPo userPo2 = new UserPo(null, "user2", "pw2");
        UserPo userPo3 = new UserPo(null, "user3", "pw3");

        List<UserPo> userPos = new ArrayList<>();
        userPos.add(userPo1);
        userPos.add(userPo2);
        userPos.add(userPo3);
        Map<String, UserPo> userPoByUsername = userPos.stream()
            .collect(Collectors.toMap(UserPo::getUsername, Function.identity()));

        int count = this.userMapper.batchInsertByMap(userPoByUsername);

        System.out.printf("count:[%d]\n", count);
        userPos.forEach(e -> System.out.println(e));
    }

    @Test
    @Sql("/db/schema.sql")
    public void testBatchInsertByEntrySet_insertDataNormally_thenGetIncrementId() {

        UserPo userPo1 = new UserPo(null, "user1", "pw1");
        UserPo userPo2 = new UserPo(null, "user2", "pw2");
        UserPo userPo3 = new UserPo(null, "user3", "pw3");

        List<UserPo> userPos = new ArrayList<>();
        userPos.add(userPo1);
        userPos.add(userPo2);
        userPos.add(userPo3);
        Map<String, String> pwByUsername = userPos.stream()
            .collect(Collectors.toMap(UserPo::getUsername, UserPo::getPassword));

        int count = this.userMapper.batchInsertByEntrySet(pwByUsername);

        System.out.printf("count:[%d]\n", count);
        userPos.forEach(e -> System.out.println(e));
    }

    @Test
    @Sql("/db/schema.sql")
    public void testBatchUpdate() {

        UserPo userPo1 = new UserPo(4L, "update1", "updatePw1");
        UserPo userPo2 = new UserPo(6L, "update2", "updatePw2");
        UserPo userPo3 = new UserPo(8L, "update3", "updatePw3");

        List<UserPo> userPos = new ArrayList<>();
        userPos.add(userPo1);
        userPos.add(userPo2);
        userPos.add(userPo3);

        int updatedCount = this.userMapper.batchUpdate(userPos);
        System.out.printf("updatedCount:[%d]\n", updatedCount);
    }
}
