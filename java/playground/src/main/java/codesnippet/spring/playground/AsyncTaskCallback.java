package codesnippet.spring.playground;

import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTaskCallback {

    private static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private SyncTask syncTask;

    @Async("taskExecutor")
    public Future<String> doTaskOneCallback() throws Exception {
        // 可以觀察到thread prefix
        LOGGER.info("doTaskOneCallback: " + Thread.currentThread().getName());
        this.syncTask.doTaskOne();
        return new AsyncResult<>("Task1 Completed");
    }

    @Async("taskExecutor")
    public Future<String> doTaskTwoCallback() throws Exception {
        LOGGER.info("doTaskTwoCallback: " + Thread.currentThread().getName());
        this.syncTask.doTaskTwo();
        return new AsyncResult<>("Task2 Completed");
    }

    @Async("taskExecutor")
    public Future<String> doTaskThreeCallback() throws Exception {
        LOGGER.info("doTaskThreeCallback: " + Thread.currentThread().getName());
        this.syncTask.doTaskThree();
        return new AsyncResult<>("Task3 Completed");
    }
}
