package codesnippet.spring.fundamentals.component;

import org.springframework.stereotype.Component;

@Component(value = "trackCoach")
public class TrackCoach implements ICoach
{
	@Override
	public String getDailyWorkout() {
		return "\nRun a hard 5k\n";
	}
}