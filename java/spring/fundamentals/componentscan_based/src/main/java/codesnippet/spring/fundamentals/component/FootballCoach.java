package codesnippet.spring.fundamentals.component;

import org.springframework.stereotype.Component;

@Component(value = "footballCoach")
public class FootballCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nSpend 1 hour on shooting practice\n";
	}
}
