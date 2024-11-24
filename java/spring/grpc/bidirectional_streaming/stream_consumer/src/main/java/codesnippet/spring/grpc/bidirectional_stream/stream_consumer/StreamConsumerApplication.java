package codesnippet.spring.grpc.bidirectional_stream.stream_consumer;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StreamConsumerApplication
{
    public static void main(String[] args) {

        SpringApplication.run(StreamConsumerApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("App started");
    }
}
