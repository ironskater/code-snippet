package codesnippet.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App
{
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void
        main( String[] args ) throws InterruptedException
    {
        getThread1().start();
        getThread2().start();
        countDownLatch.await();
        System.out.println("start main task");
    }

    private static Thread
        getThread1()
    {
        return new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("thread1 init complete");
                countDownLatch.countDown();
            } catch(InterruptedException ex) {

            }
        });
    }

    private static Thread
        getThread2()
    {
        return new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("thread2 init complete");
                countDownLatch.countDown();
            } catch(InterruptedException ex) {

            }
        });
    }
}
