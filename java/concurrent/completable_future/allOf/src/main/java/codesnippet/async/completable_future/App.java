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
		CompletableFuture<Void> combinedFuture
  			= CompletableFuture.allOf(	future1,
			  							future2,
										future3);
		// Void result = combinedFuture.get(); // result is null

        String result = combinedFuture.thenApply(__ -> {
            return Stream.of(	future1,
								future2,
								future3)
			.map(CompletableFuture::join)
			.collect(Collectors.joining(" "));
        }).get();

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            throw new IllegalArgumentException("oops");
            // return "xxxxxxxxxxx";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> names = CompletableFuture.allOf(f1, f2, f3)
            .thenApply(__ -> f1.join() + "," + f2.join() + "," + f3.join())
            .exceptionally(err -> {
                System.out.println("oops, there was a problem! " + err.getMessage());
                return "names not found!";
            });

        System.out.println("names: " + names.get());
		/**
		 * The CompletableFuture.join() method is similar to the get method
         * If we omit to invoke allOf, we’ll process the results of the CompletableFutures sequentially.
         * Consequently, we can end up with partial processing of the values.
         * In other words, if one of the CompletableFutures throws an exception,
         * it will break the chain and stop processing.
         * In some cases, this can cause errors because the prior elements were already processed:
         * On the other hand, we can combine the three instances using allOf() and
         * then invoke the join() method to achieve some sort of atomicity.
         * By doing so, we either process all elements at once or none of them:
		 */
		// String result = Stream.of(	future1,
		// 							future2,
		// 							future3)
		// 	.map(CompletableFuture::join)
		// 	.collect(Collectors.joining(" "));

		System.out.println(result);
	}
}
