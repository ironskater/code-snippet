package codesnippet.spring.event;

import java.lang.invoke.MethodHandles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;

@Component
public class CustomSpringEventPublisher
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

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