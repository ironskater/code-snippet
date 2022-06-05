package codesnippet.spring.event;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;

@Component
public class ContextEventListener
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	private volatile AtomicBoolean isInit = new AtomicBoolean(false);

	@EventListener
	public void
		handleContextRefreshedEvent(ContextRefreshedEvent ctxRefreshedEvt)
	{
		if(!isInit.compareAndSet(false, true)) {
			LOGGER.info("============= Has been init");
			return;
		}

		LOGGER.info("============= Context refresh Event received.");
	}
}