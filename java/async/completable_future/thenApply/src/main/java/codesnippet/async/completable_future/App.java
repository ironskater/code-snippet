package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App
{
	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		CompletableFuture<String> task =
			CompletableFuture.supplyAsync(() -> "Hello")
				.thenApply(s -> s + " World");

		System.out.println("to get the task result");

		String result = task.get();

		System.out.println(result);
	}
}
