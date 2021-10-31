package codesnippet.java.design_pattern.code2interface;

public class BaseballCoach implements ICoach {

	@Override
	public String getDailyWorkout() {
		return "Spend 30 mins on batting practice";
	}
}
