package codesnippet.spring.retry;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;

@Component
public class ScheduleTask
{
	private static final Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	private static final int attemptCount = 4;

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	@Retryable(	include = {UnsupportedOperationException.class},
				maxAttempts = attemptCount,
				backoff = @Backoff(value = 1000))
	@Scheduled(fixedDelay = 5000)
	public void
		scheduleTaskUsingCronExpression()
	{
		int count = atomicInteger.addAndGet(1);

		LOGGER.info("for count < 5: {}",
					count);

		throw new UnsupportedOperationException();
	}

	@Recover
	public void
		recover(UnsupportedOperationException ex)
	{
		LOGGER.info("end of retry");
	}
}
