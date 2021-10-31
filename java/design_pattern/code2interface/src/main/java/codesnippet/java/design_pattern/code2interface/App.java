package codesnippet.java.design_pattern.code2interface;

public class App
{
	public static void
		main( String[] args )
	{
		ICoach theCoach = new TrackCoach();

		System.out.println(theCoach.getDailyWorkout());
	}
}
