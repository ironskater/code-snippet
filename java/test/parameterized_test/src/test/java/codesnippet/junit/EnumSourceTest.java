package codesnippet.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import codesnippet.junit.enums.Types;

public class EnumSourceTest
{
	@ParameterizedTest
	@EnumSource
	public void
		testEnumSource(Types type)
	{
		System.out.println(type);
	}

	@ParameterizedTest
	@EnumSource(names = {"SMALL", "UNKNOWN"})
	public void
		testEnumSourcePartialInclude(Types type)
	{
		System.out.println(type);
	}

	@ParameterizedTest
	@EnumSource(mode = EnumSource.Mode.EXCLUDE,
				names = {"SMALL", "UNKNOWN"})
	public void
		testEnumSourcePartialExclude(Types type)
	{
		System.out.println(type);
	}
}
