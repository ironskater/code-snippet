package codesnippet.java.design_pattern.code2interface;

public class TrackCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nRun a hard 5k\n";
	}
}