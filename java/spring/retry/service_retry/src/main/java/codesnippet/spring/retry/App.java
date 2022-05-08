package codesnippet.spring.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;

/**
 * spring-retry
 * 1. Add EnableRetry annotation
 */
@EnableRetry
@SpringBootApplication
@EnableConfigurationProperties(codesnippet.spring.retry.configuration.AppConfig.class)
public class App
{
	public static void
		main( String[] args )
	{
		SpringApplication.run(	App.class,
								args);
	}
}
