package codesnippet.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class App
{
    private static class FactorialSquareCalculator extends RecursiveTask<Integer> {

        private Integer n;

        public FactorialSquareCalculator(Integer n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }

            FactorialSquareCalculator calculator
            = new FactorialSquareCalculator(n - 1);

            // fork is a non-blocking call
            calculator.fork();

            return n * n + calculator.join();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // calculate: 4² + 3² + 2² + 1², which is 30.
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(4);

        forkJoinPool.execute(calculator);

        while(!calculator.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(100);
        }

        System.out.println("result: " + calculator.get());

        forkJoinPool.shutdown();
    }
}
