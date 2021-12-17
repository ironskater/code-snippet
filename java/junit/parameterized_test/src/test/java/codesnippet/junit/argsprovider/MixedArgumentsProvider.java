package codesnippet.junit.argsprovider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import codesnippet.junit.enums.Types;

public class MixedArgumentsProvider implements ArgumentsProvider
{
	@Override
	public Stream<? extends Arguments>
		provideArguments(ExtensionContext context) throws Exception
	{
		return Stream.of(
			Arguments.of("user1", 1, 11.22, Types.BIG, true),
			Arguments.of("user2", 2, 22.33, Types.SMALL, false));
	}
}