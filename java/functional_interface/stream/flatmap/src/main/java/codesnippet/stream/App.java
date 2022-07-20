package codesnippet.stream;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class App
{
	public static void
		main( String[] args )
	{
        List<List<String>> nestedList = asList(
            asList("one:one"),
            asList("two:one", "two:two", "two:three"),
            asList("three:one", "three:two", "three:three", "three:four")
        );

        // The flatMap() method first flattens the input Stream of Streams to a Stream of Strings
        List<String> flatList = nestedList.stream().flatMap(Collection::stream).collect(Collectors.toList());

        flatList.forEach(System.out::println);
	}
}
