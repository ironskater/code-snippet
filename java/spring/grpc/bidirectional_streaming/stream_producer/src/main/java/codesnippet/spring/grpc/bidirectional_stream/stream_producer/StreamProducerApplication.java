package codesnippet.spring.grpc.bidirectional_stream.stream_producer;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StreamProducerApplication
{
    public static void main(String[] args) {

        SpringApplication.run(StreamProducerApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("App started");
    }
}
