package codesnippet.spring.fundamentals;

public class TrackCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nRun a hard 5k\n";
	}
}