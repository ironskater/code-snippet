package codesnippet.spring.feign;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class CustomConfiguration {

    /**
     * 攔截client，然後加上以下header
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("user", "dummy_user!!!!!!!!");
            requestTemplate.header("password", "dummy_password!!!!!!!!!");
            requestTemplate.header("Accept", "dummy_accept");
        };
    }
}
