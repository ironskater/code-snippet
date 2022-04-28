package codesnippet.spring.schedule;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import codesnippet.java_utility.Slf4jLogger;

@ComponentScan
@EnableAsync
@EnableConfigurationProperties(codesnippet.spring.schedule.AppProp.class)
public class App
{
	private static final Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	public static void
		main( String[] args )
	{
		try(ConfigurableApplicationContext ctx =
				SpringApplication.run(	App.class,
										args))
		{
			LOGGER.info("hello world");

			while(true)
			{
				Thread.sleep(10);
			}
		}
		catch(Exception ex)
		{
			LOGGER.error("Activation Failed", ex);
		}
	}

	// @Scheduled(fixedDelay = 1000)
	// public void
	// 	scheduleFixedDelayTask()
	// {
	// 	LOGGER.info("Fixed delay task - " + LocalTime.now());
	// }

	// @Scheduled(fixedRate = 2000)
	// public void
	// 	scheduleFixedRateTask()
	// {
	// 	LOGGER.info("Fixed rate task - " + LocalTime.now());
	// }

	@Async
	@Scheduled(fixedRate = 1000)
	public void
		scheduleFixedRateTaskAsync() throws InterruptedException
	{
		LOGGER.info(
			"Fixed rate task async - " + System.currentTimeMillis() / 1000);
		Thread.sleep(5000);
	}

	// @Scheduled(cron = "0 15 10 15 * ?")
	// public void
	// 	scheduleTaskUsingCronExpression()
	// {
	// 	long now = System.currentTimeMillis() / 1000;

	// 	LOGGER.info("schedule tasks using cron jobs - " + now);
	// }
}
