package codesnippet.spring.fundamentals.component;

import org.springframework.stereotype.Component;

@Component(value = "baseballCoach")
public class BaseballCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nSpend 30 mins on batting practice\n";
	}
}
