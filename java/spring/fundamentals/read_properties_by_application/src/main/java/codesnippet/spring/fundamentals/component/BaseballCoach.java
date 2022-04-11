package codesnippet.spring.fundamentals.component;

import java.lang.invoke.MethodHandles;

import org.springframework.stereotype.Component;

import codesnippet.java_utility.Slf4jLogger;
import codesnippet.spring.fundamentals.AppProp;

@Component(value = "baseballCoach")
public class BaseballCoach implements ICoach
{
	private final static Slf4jLogger LOGGER =
		new Slf4jLogger(MethodHandles.lookup().lookupClass());

	static
	{
		LOGGER.info("============================= Start calling BaseballCoach.GetDailyWorkoutStatic...");
		LOGGER.info(GetDailyWorkoutStatic()); // 會得到null value，因為BaseballCoach在被初始化成spring bean時，無法保證AppProp已初始化
		LOGGER.info("============================= End calling BaseballCoach.GetDailyWorkoutStatic...");
	}

	@Override
	public String
		getDailyWorkout()
	{
		return AppProp.getBaseball();
	}

	public static String
		GetDailyWorkoutStatic()
	{
		return AppProp.getBaseball();
	}
}
