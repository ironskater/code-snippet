package codesnippet.spring.kafka;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import codesnippet.spring.kafka.model.ContractDto;
import codesnippet.spring.kafka.model.DealDto;

@SpringBootApplication
public class App
{
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {

            DealDto dealDto = new DealDto(BigDecimal.valueOf(121), new Date());

            Message<ContractDto> message = MessageBuilder
            // .withPayload(new ContractDto(123L, "SOL_USDT", dealDto, new Date()))
            .withPayload(new ContractDto())
            .setHeader("t", "10")
            .setHeader(KafkaHeaders.TOPIC, "contract_data_bybit_market_")
            .build();

            kafkaTemplate.send(message);

            // kafkaTemplate.send("contract_data_bybit_market_", new ContractDto());
            // Thread.sleep(2000);
        };
    }

    public static void main(String[] args) {
        try(ConfigurableApplicationContext ctx =
            SpringApplication.run(	App.class,
                                    args)) {

        }
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     while(true) {
    //         this.kafkaTemplate.send("topic1", "hello world");
    //         Thread.sleep(5000);
    //     }
    // }
}
