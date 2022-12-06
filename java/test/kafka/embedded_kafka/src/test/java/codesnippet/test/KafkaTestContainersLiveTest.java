package codesnippet.test;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    KafkaConsumer.class,
    KafkaProducer.class,
    KafkaAutoConfiguration.class,
}, initializers = ConfigDataApplicationContextInitializer.class)
/**
 * @DirtiesContext make sure this context is cleaned and reset between different tests.
 */
@DirtiesContext
@TestPropertySource("/application-test.properties")
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaTestContainersLiveTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Value("${test.topic}")
    private String topic;

    @BeforeEach
    public void setup() {
        this.consumer.resetLatch();
    }

    @Test
    public void givenKafkaDockerContainer_whenSendingWithSimpleProducer_thenMessageReceived() throws Exception {

        String data = "Sending with our own simple KafkaProducer";

        producer.send(this.topic, data);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        Assertions.assertThat(messageConsumed).isTrue();
        Assertions.assertThat(consumer.getPayload()).contains(data);
    }
}
