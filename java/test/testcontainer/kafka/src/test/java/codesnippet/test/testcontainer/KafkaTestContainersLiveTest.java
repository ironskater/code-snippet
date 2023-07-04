package codesnippet.test.testcontainer;

import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * This test class uses Testcontainers to instantiate and manage an external Apache
 * Kafka broker hosted inside a Docker container.
 *
 * Therefore, one of the prerequisites for using Testcontainers is that Docker is installed on the host running this test
 */
// @Testcontainers
// @Import(codesnippet.test.testcontainer.KafkaTestContainersLiveTest.KafkaTestContainersConfiguration.class)
// @SpringBootTest
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

    /**
     * This field is an instance of the KafkaContainer class that will prepare and manage the life cycle of our container running Kafka.
     * Note that it's static
     */
    // @Container
    // public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1")).withStartupTimeout(Duration.ofSeconds(120));

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

    // @TestConfiguration
    // static class KafkaTestContainersConfiguration {

    //     @Bean
    //     ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
    //         ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    //         factory.setConsumerFactory(consumerFactory());
    //         return factory;
    //     }

    //     @Bean
    //     public ConsumerFactory<Integer, String> consumerFactory() {
    //         return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    //     }

    //     @Bean
    //     public Map<String, Object> consumerConfigs() {
    //         Map<String, Object> props = new HashMap<>();
    //         props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
    //         props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //         props.put(ConsumerConfig.GROUP_ID_CONFIG, "baeldung");
    //         props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //         props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //         return props;
    //     }

    //     @Bean
    //     public ProducerFactory<String, String> producerFactory() {
    //         Map<String, Object> configProps = new HashMap<>();
    //         configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
    //         configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //         configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //         return new DefaultKafkaProducerFactory<>(configProps);
    //     }

    //     @Bean
    //     public KafkaTemplate<String, String> kafkaTemplate() {
    //         return new KafkaTemplate<>(producerFactory());
    //     }
    // }
}