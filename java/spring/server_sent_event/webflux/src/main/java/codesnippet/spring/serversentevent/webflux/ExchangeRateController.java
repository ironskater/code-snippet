package codesnippet.spring.serversentevent.webflux;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExchangeRateController {

    @GetMapping(
        path = "/api/rates",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ExchangeRate>> streamFlux() {

        // The interval method creates a Flux that emits a mocked rate.
        return Flux.interval(Duration.ofSeconds(1))
            .map(sequence -> ServerSentEvent.<ExchangeRate>builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .event("rate-update")
                .data(generateRandomRate())
                .build());
    }

    private ExchangeRate generateRandomRate() {
        // 模擬匯率數據
        BigDecimal randomRate = BigDecimal.valueOf(30 + Math.random())
            .setScale(2, RoundingMode.HALF_UP);
        return new ExchangeRate("USD/TWD", randomRate, LocalDateTime.now());
    }
}
