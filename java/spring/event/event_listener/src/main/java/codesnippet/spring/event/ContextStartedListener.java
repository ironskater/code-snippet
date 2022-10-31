package codesnippet.spring.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ContextStartedListener
	implements ApplicationListener<ApplicationStartedEvent>
{
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void
		onApplicationEvent(ApplicationStartedEvent cse)
	{
		LOGGER.info("[EVENT] Starting application context");
	}
}
