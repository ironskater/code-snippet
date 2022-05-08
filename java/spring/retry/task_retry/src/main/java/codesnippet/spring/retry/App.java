package codesnippet.spring.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * To act with scheduling and retry, needs springboot aop starter
 */
@ComponentScan
@EnableScheduling
@EnableRetry
public class App
{
	public static void
		main( String[] args )
	{
		SpringApplication.run(	App.class,
								args);
	}
}
