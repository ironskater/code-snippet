package codesnippet.spring.fundamentals;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import codesnippet.java_utility.Slf4jLogger;
import codesnippet.spring.fundamentals.component.ICoach;

@ComponentScan
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
			ICoach theCoach;

			theCoach = ctx.getBean("baseballCoach", ICoach.class);
			LOGGER.info("\n" + theCoach.getDailyWorkout());

			theCoach = ctx.getBean("footballCoach", ICoach.class);
			LOGGER.info("\n" + theCoach.getDailyWorkout());

			theCoach = ctx.getBean("trackCoach", ICoach.class);
			LOGGER.info("\n" + theCoach.getDailyWorkout());
		}
		catch(Exception ex)
		{
			LOGGER.error("Activation Failed", ex);
		}
	}
}
