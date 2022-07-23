package codesnippet.spring.fundamentals;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import codesnippet.java_utility.Slf4jLogger;
import codesnippet.spring.fundamentals.service.CustomMapFromListDynamicAutowireService;

@ComponentScan
public class App
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

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

            LOGGER.info("is US server alive? " + service.isServerActive("US", 1));
            LOGGER.info("is GB server alive? " + service.isServerActive("GB", 1));
        }
    }
}
