package codesnippet.spring.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask implements Task {

    private Task syncTask;

    @Autowired
    public AsyncTask(Task syncTask) {
        this.syncTask = syncTask;
    }

    @Override
    @Async
    public void doTaskOne() throws Exception {
        this.syncTask.doTaskOne();
    }

    @Override
    @Async
    public void doTaskTwo() throws Exception {
        this.syncTask.doTaskTwo();
    }

    @Override
    @Async
    public void doTaskThree() throws Exception {
        this.syncTask.doTaskThree();
    }
}
