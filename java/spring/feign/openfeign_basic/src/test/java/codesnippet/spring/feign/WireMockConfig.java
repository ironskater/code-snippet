package codesnippet.spring.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.tomakehurst.wiremock.WireMockServer;

@TestConfiguration
public class WireMockConfig {

    @Value("${wireMockServer.port}")
    private int port;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockBooksService() {
        return new WireMockServer(port);
    }
}
