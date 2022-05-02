package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App
{
	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		CompletableFuture<String> future1
			= CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<String> future2
			= CompletableFuture.supplyAsync(() -> "Beautiful");

		CompletableFuture<String> future3
			= CompletableFuture.supplyAsync(() -> "World");

		System.out.println("to get the task result");

		/**
		 * The CompletableFuture.allOf static method allows to wait for completion of all of the Futures provided as a var-arg
		 *
		 * The limitation of CompletableFuture.allOf() is that it does not return the combined results of all Futures.
		 * Instead, we have to manually get results from Futures.
		 */
		// CompletableFuture<Void> combinedFuture
  		// 	= CompletableFuture.allOf(	future1,
		// 	  							future2,
		// 								future3);
		// Void result = combinedFuture.get(); // result is null

		/**
		 * The CompletableFuture.join() method is similar to the get method
		 */
		String result = Stream.of(	future1,
									future2,
									future3)
			.map(CompletableFuture::join)
			.collect(Collectors.joining(" "));

		System.out.println(result);
	}
}
