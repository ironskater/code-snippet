package codesnippet.webflux.simple_webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        GreetingClient greetingClient = context.getBean(GreetingClient.class);

        String message = greetingClient.getMessage().block();

        System.out.println(message);

        context.close();
	}
}
