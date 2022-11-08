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
        private int param;

        public PrintTask(int param)
        {
            this.param = param;
        }

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
            return this.param;
        }
    }

    public static void
        main( String[] args ) throws InterruptedException, ExecutionException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future1 = executor.submit(new PrintTask(5566));

        Future<Integer> future2 = executor.submit(new Callable<Integer>() {

            @Override
            public Integer
                call() throws Exception
            {
                System.out.println("hello baby world");
                return 2266;
            }
        });

        System.out.println("waiting for result...");
        System.out.println("future1 result: " + future1.get().intValue()); // block
        System.out.println("future2 result: " + future2.get().intValue()); // block
        executor.shutdown();
    }
}
