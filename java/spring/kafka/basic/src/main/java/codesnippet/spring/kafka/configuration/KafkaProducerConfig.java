package codesnippet.spring.kafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
        configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            JsonSerializer.class);
        configProps.put(JsonSerializer.TYPE_MAPPINGS,
            "1:codesnippet.spring.kafka.model.ContractDto");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * KafkaTemplate wraps a Producer instance and provides convenience methods for sending messages to Kafka topics.
     *
     * Producer instances are thread safe.
     * So, using a single instance throughout an application context will give higher performance.
     * Consequently, KakfaTemplate instances are also thread safe, and use of one instance is recommended.
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> produceFactory) {
        return new KafkaTemplate<>(produceFactory);
    }
}
