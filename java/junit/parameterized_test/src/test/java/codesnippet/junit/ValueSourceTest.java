package codesnippet.junit;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceTest
{
	@ParameterizedTest
	@ValueSource(ints = {2, 4, 8})
	public void
		testNumberShouldBeEven(int num)
	{
		System.out.println(num);
	}

	@ParameterizedTest
	@ValueSource(strings = {"Effective Java", "Code Complete", "Clean Code"})
	public void
		testPrintTitle(String title)
	{
		System.out.println(title);
	}

	@ParameterizedTest
	@ValueSource(strings = {"2021-12-25"})
	public void
		testPrintDate(LocalDate date)
	{
		System.out.println(date);
	}

	@ParameterizedTest
	@ValueSource(strings = {"31.12.2021", "01.01.1970"})
	public void
		testConverter(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate date)
	{
		System.out.println(date);
	}
}
