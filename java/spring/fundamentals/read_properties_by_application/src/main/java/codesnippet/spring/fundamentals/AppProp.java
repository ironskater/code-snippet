package codesnippet.spring.fundamentals;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "trainning-item")
@ConstructorBinding
public final class AppProp
{
	private static String baseball;

	public AppProp(String baseball)
	{
		AppProp.baseball = baseball;
	}

	public static String
		getBaseball()
	{
		return baseball;
	}
}
