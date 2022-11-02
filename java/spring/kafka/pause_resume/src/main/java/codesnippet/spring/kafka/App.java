package codesnippet.spring.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.event.KafkaEvent;

@SpringBootApplication
public class App implements ApplicationListener<KafkaEvent> {

    private static final String TOPIC = "pause.resume.topic";
    private static final String LISTENER_ID = "pause.resume";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args).close();
    }

    @Override
    public void onApplicationEvent(KafkaEvent event) {
        /**
         * 當收到 pause event時，會印出
         * ConsumerPausedEvent [reason=User requested, partitions=[pause.resume.topic-1, pause.resume.topic-0]]
         *
         * 當收到 resume event時，會印出
         * ConsumerResumedEvent [partitions=[pause.resume.topic-1, pause.resume.topic-0]]
         */
        System.out.println(event);
    }

    @Bean
    public ApplicationRunner runner(KafkaListenerEndpointRegistry registry,
            KafkaTemplate<String, String> template) {
        return args -> {
            String data = "thing1";
            template.send(TOPIC, data);
            System.out.println("\n\nsend data: " + data);
            Thread.sleep(2_000);

            System.out.println("pausing");
            registry.getListenerContainer(LISTENER_ID).pause(); // 同時發出pause event
            Thread.sleep(2_000);

            data = "thing2";
            template.send(TOPIC, data);
            System.out.println("send data: " + data);

            data = "thing3";
            template.send(TOPIC, data);
            System.out.println("send data: " + data);

            data = "thing4";
            template.send(TOPIC, data);
            System.out.println("send data: " + data);

            Thread.sleep(5_000);

            System.out.println("resuming");

            // 同時發出resume event, resume之後listener才會繼續收到thing2~thing4的資料
            registry.getListenerContainer(LISTENER_ID).resume();
        };
    }

    @KafkaListener(id = LISTENER_ID, topics = TOPIC)
    public void listen(String in) {
        System.out.println("receive data: " + in);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC)
            .partitions(2)
            .replicas(1)
            .build();
    }

}
