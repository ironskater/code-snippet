package codesnippet.spring.serversentevent.basic;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Slf4j
public class ExchangeRateService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Scheduled(fixedRate = 1000) // 每1秒更新一次
    public void updateRates() {
        log.info("Updating rates");
        ExchangeRate rate = generateRandomRate();
        emitters.removeIf(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                    .data(rate)
                    .id(String.valueOf(System.currentTimeMillis()))
                    .name("rate-update"));
                return false;
            } catch (IOException e) {
                log.error("Error sending rate: ", e);
                return true;  // 移除失敗的 emitter
            }
        });
    }

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> {
            emitters.remove(emitter);
        });

        emitter.onTimeout(() -> {
            emitters.remove(emitter);
        });

        emitters.add(emitter);
        return emitter;
    }

    private ExchangeRate generateRandomRate() {
        // 模擬匯率數據
        BigDecimal randomRate = BigDecimal.valueOf(30 + Math.random())
            .setScale(2, RoundingMode.HALF_UP);
        return new ExchangeRate("USD/TWD", randomRate, LocalDateTime.now());
    }
}