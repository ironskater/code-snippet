package codesnippet.spring.fundamentals;

public class FootballCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nSpend 1 hour on shooting practice\n";
	}
}
