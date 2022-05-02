package codesnippet.async.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App
{
	private static ExecutorService executor = Executors.newCachedThreadPool();

	public static void
		main( String[] args ) throws 	InterruptedException,
										ExecutionException
	{
		Future<String> task = calculateAsync();

		/**
		 * If we already know the result of a computation,
		 * we can use the static completedFuture method with an argument that represents a result of this computation.
		 * Consequently, the get method of the Future will never block, immediately returning this result instead
		 */
		// Future<String> task = CompletableFuture.completedFuture("Hello world");

		System.out.println("to get the task result");
		// use the get method to block the current thread until this result is provided.
		String result = task.get();

		System.out.println(result);

		executor.shutdown();
	}

	private static Future<String>
		calculateAsync() throws InterruptedException
	{
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		executor.submit(() -> {
			Thread.sleep(500);
			// complete it at some time(500ms) in the future using the complete method
			// complete方法代表完成非同步執行，並回傳結果
			System.out.println("to complete the task");
			completableFuture.complete("Hello world");

			return null;
		});

		return completableFuture;
	}
}
