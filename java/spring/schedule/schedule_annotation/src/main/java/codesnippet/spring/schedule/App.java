package codesnippet.spring.schedule;

import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@ComponentScan
@EnableAsync
@EnableConfigurationProperties(codesnippet.spring.schedule.AppProp.class)
public class App
{
    private static final Logger log = LogManager.getLogger();

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

    /**
     * 上一次task的執行結束時間與這一次task的執行開始時間會固定差fixedDelay
     */
	@Scheduled(fixedDelay = 5000)
	public void
		scheduleFixedDelayTask()
	{
		log.info("Fixed delay task - start. datetime[{}]", LocalTime.now());

        int count = 0;

        for (int ix = 0; ix < 1000000000; ix++) {
            count++;
        }

		log.info("Fixed delay task - end.   datetime[{}], count[{}]", LocalTime.now(), count);
	}

	// @Scheduled(fixedRate = 2000)
	// public void
	// 	scheduleFixedRateTask()
	// {
	// 	LOGGER.info("Fixed rate task - " + LocalTime.now());
	// }

	// @Async
	// @Scheduled(fixedRate = 1000)
	// public void
	// 	scheduleFixedRateTaskAsync() throws InterruptedException
	// {
	// 	System.out.println(
	// 		"Fixed rate task async - " + System.currentTimeMillis() / 1000);
	// 	Thread.sleep(5000);
	// }

	// @Scheduled(cron = "0 15 10 15 * ?")
	// public void
	// 	scheduleTaskUsingCronExpression()
	// {
	// 	long now = System.currentTimeMillis() / 1000;

	// 	LOGGER.info("schedule tasks using cron jobs - " + now);
	// }
}
