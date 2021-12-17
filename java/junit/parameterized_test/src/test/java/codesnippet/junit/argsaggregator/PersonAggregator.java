package codesnippet.junit.argsaggregator;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import codesnippet.junit.enums.Types;

public class PersonAggregator implements ArgumentsAggregator
{
	@Override
	public Object aggregateArguments(	ArgumentsAccessor args,
										ParameterContext context)
			throws ArgumentsAggregationException
	{
		Person person = new Person();
		person.setFirstName(args.getString(0));
		person.setLastName(args.getString(1));
		person.setType(args.get(2, Types.class));

		return person;
	}
}