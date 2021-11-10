package codesnippet.spring.fundamentals;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import codesnippet.java_utility.Slf4jLogger;

@ImportResource("classpath:applicationContext.xml")
public class App
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	public static void
		main( String[] args )
	{
		try(ConfigurableApplicationContext ctx =
				SpringApplication.run(	App.class,
										args))
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
