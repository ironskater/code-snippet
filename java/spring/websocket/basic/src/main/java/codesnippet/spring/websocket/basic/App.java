package codesnippet.spring.websocket.basic;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class App
{
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("App started");
    }
}
