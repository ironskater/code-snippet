package codesnippet.java.design_pattern.code2interface;

public class FootballCoach implements ICoach {

	@Override
	public String getDailyWorkout() {
		return "\nSpend 1 hour on shooting practice\n";
	}
}
