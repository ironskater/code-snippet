package codesnippet.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class App
{
    @KafkaListener(id = "consumer1", topics = "topic1")
    public void consume(String message) {

        System.out.println(message);
    }

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
