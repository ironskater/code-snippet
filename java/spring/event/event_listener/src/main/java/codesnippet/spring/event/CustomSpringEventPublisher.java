package codesnippet.spring.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher
{
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void
		publishCustomEvent(final String message)
	{
		LOGGER.info("Publishing custom event.");

		applicationEventPublisher.publishEvent(
			new CustomSpringEvent(	this,
									message));
	}
}