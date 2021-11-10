package codesnippet.java.design_pattern.code2interface;

import java.lang.invoke.MethodHandles;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import codesnippet.java_utility.Slf4jLogger;

public class App
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	public static void
		main( String[] args )
	{
		try(ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml"))
		{
			ICoach theCoach = ctx.getBean("myCoach", ICoach.class);

			LOGGER.info("\n" + theCoach.getDailyWorkout());

			ctx.close();
		}
		catch(Exception ex)
		{
		}
	}
}
