package codesnippet.spring.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void initRedisData() throws JsonProcessingException {

        this.stringRedisTemplate.opsForValue().set("redis--3", "redis cache 1");
        this.stringRedisTemplate.opsForValue().set("randowKey", "randomValue");

        User user = new User("hydeliao", "pw");
        this.stringRedisTemplate.opsForValue().set("user1", objectMapper.writeValueAsString(user));
    }

    @RequestMapping("/")
    public String get() {

        return this.stringRedisTemplate.opsForValue().get("user1");
    }
}
