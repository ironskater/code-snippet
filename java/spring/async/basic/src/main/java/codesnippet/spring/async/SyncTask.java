package codesnippet.spring.async;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SyncTask implements Task {

    private static Logger LOGGER = LogManager.getLogger();
    private static Random random = new Random();

    @Override
    public void doTaskOne() throws Exception {
        LOGGER.info("Start Task1");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        LOGGER.info("Task1 completed, durataion: " + (end - start) + "ms");
    }

    @Override
    public void doTaskTwo() throws Exception {
        LOGGER.info("Start Task2");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        LOGGER.info("Task2 completed, duration:" + (end - start) + "ms");
    }

    @Override
    public void doTaskThree() throws Exception {
        LOGGER.info("Start Task3");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        LOGGER.info("Task3 completed, duration:" + (end - start) + "ms");
    }
}
