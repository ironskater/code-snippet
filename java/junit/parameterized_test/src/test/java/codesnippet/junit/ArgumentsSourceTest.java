package codesnippet.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import codesnippet.junit.argsprovider.MixedArgumentsProvider;
import codesnippet.junit.argsprovider.MyArgumentsProvider;
import codesnippet.junit.enums.Types;

public class ArgumentsSourceTest
{
	@ParameterizedTest
	@ArgumentsSource(MyArgumentsProvider.class)
	public void
		testArgumentsSource(String arg)
	{
		System.out.println(arg);
	}

	@ParameterizedTest
	@ArgumentsSource(MixedArgumentsProvider.class)
	public void
		testMixedArgumentsSource(	String usr,
									int id,
									double score,
									Types type,
									boolean isRead)
	{
		System.out.println(usr);
		System.out.println(id);
		System.out.println(score);
		System.out.println(type);
		System.out.println(isRead);
		System.out.println("-----------------------------------");
	}
}