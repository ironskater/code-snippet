package codesnippet.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * As of Spring Framework 4.2, the ApplicationEventPublisher interface provides a new overload for the publishEvent(Object event)
 * method that accepts any object as the event. 
 * Therefore, Spring events no longer need to extend the ApplicationEvent class.
 */
public final class CustomSpringEvent
	extends ApplicationEvent // comment it that the code still works
{
	private String message;

	public CustomSpringEvent(	Object source,
								String message)
	{
		super(source);
		this.message = message;
	}

	public String
		getMessage()
	{
		return this.message;
	}
}
