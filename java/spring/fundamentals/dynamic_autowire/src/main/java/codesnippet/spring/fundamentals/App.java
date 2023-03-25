package codesnippet.spring.fundamentals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import codesnippet.spring.fundamentals.service.CustomMapFromListDynamicAutowireService;

@ComponentScan
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
            CustomMapFromListDynamicAutowireService service =
                ctx.getBean("customMapFromListDynamicAutowireService",
                            CustomMapFromListDynamicAutowireService.class);

            LOGGER.info("is US server alive? {}", service.isServerActive("US", 1));
            LOGGER.info("is GB server alive? {}", service.isServerActive("GB", 1));
        }
    }
}
