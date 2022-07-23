package codesnippet.spring.fundamentals;

import java.lang.invoke.MethodHandles;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import codesnippet.java_utility.Slf4jLogger;

@ImportResource("classpath:applicationContext.xml")
public class App
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

    public static void
        main( String[] args )
    {
        // // without @ImportResource
        // try(ClassPathXmlApplicationContext ctx =
        //         new ClassPathXmlApplicationContext("applicationContext.xml")) {

        //     ICoach coach = ctx.getBean("myCoach", ICoach.class);
        //     LOGGER.info(coach.getDailyFortune());
        // }

        // with @ImportResource
        try(ConfigurableApplicationContext ctx =
                SpringApplication.run(	App.class,
                                        args))
        {
            ICoach coach = ctx.getBean("myCoach", ICoach.class);
            LOGGER.info("\n" + coach.getDailyWorkout());
            LOGGER.info("\n" + coach.getDailyFortune());

            FootballCoach fCoach = ctx.getBean("myFootballCoach", FootballCoach.class);
            LOGGER.info(fCoach.getDailyWorkout());
            LOGGER.info(fCoach.getDailyFortune());
            LOGGER.info(fCoach.getEmailAddress());
            LOGGER.info(fCoach.getTeam());
            LOGGER.info("" + fCoach.getAge());
        }
    }
}
