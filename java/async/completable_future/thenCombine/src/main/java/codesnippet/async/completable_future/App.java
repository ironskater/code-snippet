package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public class App
{
	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		CompletableFuture<String> task1 =
			CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<String> task2 =
			CompletableFuture.supplyAsync(() -> " World");

		BiFunction<String, String, String> handler =
			(s1, s2) -> s1 + s2;

		System.out.println("to get the task result");

		String result = task1.thenCombine(	task2,
											handler)
							.get();

		System.out.println(result);

		/**
		 * A simpler case is when we want to do something with two Futures‘ results,
		 * but don't need to pass any resulting value down a Future chain. The thenAcceptBoth method is there to help:
		 */
		// CompletableFuture<Void> future =
		// 	CompletableFuture.supplyAsync(() -> "hello")
		// 		.thenAcceptBoth(
		// 			CompletableFuture.supplyAsync(() -> " world"),
		// 			(s1, s2) -> System.out.println(s1 + s2));
	}
}
