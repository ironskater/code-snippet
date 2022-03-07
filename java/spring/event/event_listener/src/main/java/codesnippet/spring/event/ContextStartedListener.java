package codesnippet.spring.event;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;

@Component
public class ContextStartedListener
	implements ApplicationListener<ApplicationStartedEvent>
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void
		onApplicationEvent(ApplicationStartedEvent cse)
	{
		LOGGER.info("[EVENT] Starting application context");
	}
}
