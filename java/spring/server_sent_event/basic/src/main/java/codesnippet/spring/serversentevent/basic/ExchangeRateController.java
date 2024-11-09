package codesnippet.spring.serversentevent.basic;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExchangeRateController {

    private final ExchangeRateService rateService;

    public ExchangeRateController(ExchangeRateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamRates() {
        return rateService.subscribe();
    }
}