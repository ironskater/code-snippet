package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class App
{
	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		Consumer<String> consumer =
			s -> System.out.println("Computation returned: " + s);

		CompletableFuture<Void> task =
			CompletableFuture.supplyAsync(() -> "Hello world")
				.thenAccept(consumer);

		Void result = task.get();

		System.out.println("to get the task result");
		System.out.println(result); // result is null
	}
}
