package codesnippet.junit;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NullAndEmptySourceTest
{
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = {"2021-12-24"})
	@CsvSource(value = {"2021-12-25"})
	public void
		testNullSource(LocalDate date)
	{
		System.out.println(date);
	}

	@ParameterizedTest
	@EmptySource
	public void
		testEmptySource(String date)
	{
		Assertions.assertEquals("", date);
	}

	@ParameterizedTest
	@NullAndEmptySource
	public void
		testNullAndEmptySource(String date)
	{
		if(date == null)
		{
			System.out.println("null");
		}
		else if("".equals(date))
		{
			System.out.println("empty string");
		}
		else
		{
			System.out.println("Unknown type");
		}
	}
}