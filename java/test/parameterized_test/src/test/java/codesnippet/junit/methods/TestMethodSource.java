package codesnippet.junit.methods;

import java.util.stream.Stream;

public class TestMethodSource
{
	public static Stream<String>
		stringProvider()
	{
		return Stream.of("orange", "melon");
	}
}