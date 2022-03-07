package codesnippet.spring.event;

import java.lang.invoke.MethodHandles;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;

@Component
public class CustomSpringEventListener
	implements ApplicationListener<CustomSpringEvent>
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void
		onApplicationEvent(CustomSpringEvent event)
	{
		LOGGER.info("Received spring custom event - " + event.getMessage());
	}
}
