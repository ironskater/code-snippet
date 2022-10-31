package codesnippet.spring.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener
	// implements ApplicationListener<CustomSpringEvent>
{
	private static final Logger LOGGER = LogManager.getLogger();

	// @Override
    @EventListener
	public void
		onApplicationEvent(CustomSpringEvent event)
	{
		LOGGER.info("Received spring custom event - " + event.getMessage());
	}

    @EventListener
    @Async
    public void asyncListener(CustomSpringEvent event) {

		LOGGER.info("Async Received spring custom event - " + event.getMessage());
    }
}
