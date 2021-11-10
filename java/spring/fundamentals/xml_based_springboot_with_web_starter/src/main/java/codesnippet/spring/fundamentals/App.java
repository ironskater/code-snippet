package codesnippet.spring.fundamentals;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import codesnippet.java_utility.Slf4jLogger;

/**
 * 在加入了spring-boot-starter-web的前提下，若是沒有加@SpringBootApplication
 * spring就會因為Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean而fail
 * [solution 1]
 * 加上@SpringBootApplication
 * [solution 2]
 * 採用以下method2的方法
 */
// @SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class App
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	public static void
		main( String[] args )
	{
		// [method 1]
		// try(ConfigurableApplicationContext ctx =
		// 		SpringApplication.run(	App.class,
		// 								args))
		// {
		// 	ICoach theCoach = ctx.getBean("myCoach", ICoach.class);
		// 	LOGGER.info("\n" + theCoach.getDailyWorkout());
		// }
		// catch(Exception ex)
		// {
		// 	LOGGER.error("Activation Failed", ex);
		// }

		// [method 2]
		try(ConfigurableApplicationContext ctx =
			new SpringApplicationBuilder(App.class)
				.bannerMode(Banner.Mode.OFF)
        		.logStartupInfo(false)
				.web(WebApplicationType.NONE)
				.run(args))
		{
			ICoach theCoach = ctx.getBean("myCoach", ICoach.class);
			LOGGER.info("\n" + theCoach.getDailyWorkout());
		}
		catch(Exception ex)
		{
			LOGGER.error("Activation Failed", ex);
		}
	}
}
