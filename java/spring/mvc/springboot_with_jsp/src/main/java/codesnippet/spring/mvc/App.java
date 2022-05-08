package codesnippet.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * If we need to deploy in a web container, we need to extend SpringBootServletInitializer.
 * This binds our application's Servlet, Filter, and ServletContextInitializer to the runtime server,
 * which is necessary for our application to run
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder
		configure(SpringApplicationBuilder builder)
	{
		return builder.sources(App.class);
	}

	public static void
		main( String[] args )
	{
		SpringApplication.run(App.class);
	}
}
