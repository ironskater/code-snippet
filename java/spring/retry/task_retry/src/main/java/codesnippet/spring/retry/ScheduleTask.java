package codesnippet.spring.retry;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleTask
{
	private static final int attemptCount = 4;

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	@Retryable(	include = {UnsupportedOperationException.class},
				maxAttempts = attemptCount,
				backoff = @Backoff(value = 1000))
	@Scheduled(fixedDelay = 5000)
	public void scheduleTaskUsingCronExpression()
	{
		int count = atomicInteger.addAndGet(1);

		log.info("for count < 5: {}", count);

		throw new UnsupportedOperationException();
	}

	@Recover
	public void recover(UnsupportedOperationException ex)
	{
		log.info("end of retry");
	}
}
