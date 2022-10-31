package codesnippet.spring.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan
@EnableAsync
public class App
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static void
        main( String[] args )
    {
        try(ConfigurableApplicationContext ctx =
                SpringApplication.run(	App.class,
                                        args))
        {
            LOGGER.info("Container started");

            CustomSpringEventPublisher publisher =
                ctx.getBean(CustomSpringEventPublisher.class);

            LOGGER.info("Before publish my custom event");

            publisher.publishCustomEvent("[EVENT] publish my custom event");

            LOGGER.info("After publish my custom event");
        }
        catch(Exception ex)
        {
            LOGGER.error("Activation Failed", ex);
        }
    }
}
