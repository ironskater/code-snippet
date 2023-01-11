package codesnippet.spring.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@TestConfiguration
public class FeignConfig {

    @Value("${book.service.url}")
    private String bookServiceUrl;

    @Bean
    public BooksClient booksClient() {
        return Feign.builder()
            // 預設不輸出null value
            .encoder(new JacksonEncoder())
            // 預設關閉DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            .decoder(new JacksonDecoder())
            .target(BooksClient.class, bookServiceUrl);
    }
}
