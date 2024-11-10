package codesnippet.spring.webflux.invoke_reactive_api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ExchangeRateReactiveClient {

    private final WebClient webClient;

    public ExchangeRateReactiveClient(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Flux<ServerSentEvent<ExchangeRate>> getExchangeRates() {
        return webClient.get()
            .uri("/api/rates")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<ExchangeRate>>() {});
    }
}
