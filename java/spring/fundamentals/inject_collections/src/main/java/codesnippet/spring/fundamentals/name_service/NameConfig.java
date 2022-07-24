package codesnippet.spring.fundamentals.name_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class NameConfig
{
    @Bean
    @Order(3)
    public Name john() {
        return new Name("john");
    }

    @Bean
    @Order(2)
    public Name adam() {
        return new Name("adam");
    }

    /**
     * We can specify the order of the beans while injecting into the collection.
     * @return
     */
    @Bean
    @Order(1)
    /**
     * By using @Qualifier, we specify that the bean with the name “harry” will be injected into the field named “nameList” in NameCollections class.
     * @return
     */
    // @Qualifier("harry")
    public Name harry() {
        return new Name("harry");
    }

    @Bean
    public NameCollections nameCollections() {
        return new NameCollections();
    }
}
