package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class App
{
	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		/**
		 * thenCompose method receives a function that returns another object of the same type.
		 */
		CompletableFuture<String> task1 =
			CompletableFuture.supplyAsync(() -> "Hello");

		Function<? super String, ? extends CompletionStage<String>> task2 =
			s -> CompletableFuture.supplyAsync(() -> s + " World");

		System.out.println("to get the task result");

		String result = task1.thenCompose(task2).get();

		System.out.println(result);
	}
}
