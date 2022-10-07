package codesnippet.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @MethodSource是藉由指定一個方法名稱，將該方法回傳的元素集合作為測試方法的參數
 */
public class MethodSourceTest
{
	private static Stream<String>
		stringProvider1()
	{
		return Stream.of("apple1", "banana1");
	}

	private static Stream<String>
		stringProvider2()
	{
		List<String> fruitList = new ArrayList<>();
		fruitList.add("apple2");
		fruitList.add("banana2");

		return fruitList.stream();
	}

	@ParameterizedTest
	@MethodSource(value = {"stringProvider1", "stringProvider2"})
	public void
		testMethodSource(String fruit)
	{
		System.out.println(fruit);
	}

	@ParameterizedTest
	@MethodSource("codesnippet.junit.methods.TestMethodSource#stringProvider")
	public void
		testMethodSourceFromOuterSource(String fruit)
	{
		System.out.println(fruit);
	}
}
