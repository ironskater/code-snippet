package codesnippet.spring.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@ComponentScan
@EnableAsync
@EnableConfigurationProperties(codesnippet.spring.schedule.AppProp.class)
public class App
{
	public static void
		main( String[] args )
	{
		try(ConfigurableApplicationContext ctx =
				SpringApplication.run(	App.class,
										args))
		{
			System.out.println("hello world");

			while(true)
			{
				Thread.sleep(10);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Activation Failed");
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
		System.out.println(
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
