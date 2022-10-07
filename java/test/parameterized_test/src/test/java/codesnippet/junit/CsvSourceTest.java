package codesnippet.junit;

import java.text.MessageFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import codesnippet.junit.argsaggregator.CsvToPerson;
import codesnippet.junit.argsaggregator.Person;
import codesnippet.junit.argsaggregator.PersonAggregator;
import codesnippet.junit.enums.Types;

public class CsvSourceTest
{
	@ParameterizedTest
	@CsvSource(value = {
		"1, One ",
		"2, Two ",
		"3, Three "
	},
	delimiter = ',',
	ignoreLeadingAndTrailingWhitespace = false) // default is true, since 5.8
	public void
		testDataFromCsv(long id, String name)
	{
		System.out.println(
			MessageFormat.format(	"id:[{0}], name:[{1}]",
									id,
									name));
	}

	/**
	 * Topic: outsource the csv file
	 * REF: stackoverflow - csv file is not recognized in junit5
	 */
	@ParameterizedTest
	@CsvFileSource(	resources = {"/csvFileSource.csv"},
					delimiter = ',',
					numLinesToSkip = 1)
	public void
		testDataFromCsvFileSourceByResources(long id, String name)
	{
		System.out.println(
			MessageFormat.format(	"id:[{0}], name:[{1}]",
									id,
									name));
	}

	@ParameterizedTest
	@CsvFileSource(	files = {"src/test/resources/csvFileSource.csv"},
					delimiter = ',',
					numLinesToSkip = 1)
	public void
		testDataFromCsvFileSourceByFiles(long id, String name)
	{
		System.out.println(
			MessageFormat.format(	"id:[{0}], name:[{1}]",
									id,
									name));
	}

	@ParameterizedTest
	@CsvSource(value = {"2021-12-25"})
	public void
		testLocalDateFromCsv(LocalDate date)
	{
		System.out.println(date);
	}

	@ParameterizedTest
	@CsvSource(value = {
		"NIL, 3"
	}, nullValues = "NIL")
	public void
		testNullValue(String fruit, int rank)
	{
		Assertions.assertNotNull(fruit);
		// System.out.println(MessageFormat.format("[{0}], [{1}]",
		// 										fruit,
		// 										rank));
	}

	@ParameterizedTest
	@CsvSource({"Jane, Doe, BIG",
				"John, Doe, SMALL"})
	public void
		testArgumentsAccessor(ArgumentsAccessor argsAccesseor)
	{
		System.out.println(argsAccesseor.getString(0));
		System.out.println(argsAccesseor.getString(1));
		System.out.println(argsAccesseor.get(	2,
												Types.class));

		System.out.println("--------------------------------");
	}

	@ParameterizedTest
	@CsvSource({"Jane, Doe, BIG",
				"John, Doe, SMALL"})
	public void
		testArgumentsAccessorWithCustomClass(@AggregateWith(PersonAggregator.class) Person person)
	{
		System.out.println(person);
		System.out.println("--------------------------------");
	}

	@ParameterizedTest
	@CsvSource({"Jane, Doe, BIG",
				"John, Doe, SMALL"})
	public void
		testArgumentsAccessorWithCustomClassAndAnnotation(@CsvToPerson Person person)
	{
		System.out.println(person);
		System.out.println("--------------------------------");
	}
}
