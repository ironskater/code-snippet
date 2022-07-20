package codesnippet.concurrent;

public class App
{
    // add/remove volatile to observe the different side effect
    static volatile int count;

    public static void
        main( String[] args )
    {
        getReadThread().start();
        getWriteThread().start();
    }

    private static Thread
        getReadThread()
    {
        return new Thread(() -> {
            int temp = 0;
            while(true) {
                if(temp != count) {
                    temp = count;
                    System.out.println("reader: count = " + count);
                }
            }
        });
    }

    private static Thread
        getWriteThread()
    {
        return new Thread(() -> {
            for(int ix = 0; ix < 5; ix++) {
                count++;
                System.out.println("writer: count = " + count);

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException itrEx) {
                    itrEx.printStackTrace();
                }
            }

            System.exit(0);
        });
    }
}
