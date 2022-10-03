package codesnippet.spring.async;

import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTaskCallbackTest {

    private static Logger LOGGER = LogManager.getLogger();
    private AsyncTaskCallback asyncTaskCallback;

    @Autowired
    public AsyncTaskCallbackTest(AsyncTaskCallback asyncTaskCallback) {
        this.asyncTaskCallback = asyncTaskCallback;
    }

    @Test
    public void test() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = this.asyncTaskCallback.doTaskOneCallback();
        Future<String> task2 = this.asyncTaskCallback.doTaskTwoCallback();
        Future<String> task3 = this.asyncTaskCallback.doTaskThreeCallback();

        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            Thread.sleep(10);
        }

        LOGGER.info("Total duration: " + (System.currentTimeMillis() - start) + "ms");
    }
}
