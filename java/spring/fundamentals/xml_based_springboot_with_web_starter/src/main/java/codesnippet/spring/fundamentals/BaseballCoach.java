package codesnippet.spring.fundamentals;

public class BaseballCoach implements ICoach {

	@Override
	public String getDailyWorkout() {
		return "\nSpend 30 mins on batting practice\n";
	}
}
