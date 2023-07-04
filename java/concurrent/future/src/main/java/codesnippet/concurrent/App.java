package codesnippet.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App
{
    public static void
        main( String[] args ) throws InterruptedException, ExecutionException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> result = App.calculateSquare(executor, 64);

        System.out.println("Waiting for result");

        while(!result.isDone()) {
            System.out.println("Waiting for result is done");
            Thread.sleep(100);
        }

        System.out.println("Result is " + result.get());

        executor.shutdown();
    }

    private static Future<Integer> calculateSquare(ExecutorService executor, int input) {

        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
