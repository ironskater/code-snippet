package codesnippet.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App
{
    private static class PrintTask implements Callable<Integer>
    {
        @Override
        public Integer
            call()
        {
            try {
                Thread.sleep(5000);
            } catch(InterruptedException itrEx) {
                itrEx.printStackTrace();
            }

            System.out.println("hello world");
            return 56;
        }
    }

    public static void
        main( String[] args ) throws InterruptedException, ExecutionException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new PrintTask());
        System.out.println("waiting for result...");
        System.out.println("future result: " + future.get().intValue());
        executor.shutdown();
    }
}
