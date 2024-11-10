package codesnippet.spring.serversentevent.webflux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRate {
    private String currency;
    private BigDecimal rate;
    private LocalDateTime timestamp;
}