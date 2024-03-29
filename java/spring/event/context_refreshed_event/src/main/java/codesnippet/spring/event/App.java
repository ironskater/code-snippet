package codesnippet.spring.event;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import codesnippet.java_utility.Slf4jLogger;

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
			LOGGER.info("Container started");
		}
		catch(Exception ex)
		{
			LOGGER.error("Activation Failed", ex);
		}
	}
}
