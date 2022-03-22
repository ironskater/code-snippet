package codesnippet.spring.fundamentals;

import org.springframework.stereotype.Component;

@Component(value = "myCoach")
public class BaseballCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nSpend 30 mins on batting practice\n";
	}
}
