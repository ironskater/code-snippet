package codesnippet.spring.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SyncTaskTest {

    private Task syncTask;

    @Autowired
    public SyncTaskTest(Task syncTask) {
        this.syncTask = syncTask;
    }

    /**
     * We can observe the output is not completed.
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        this.syncTask.doTaskOne();
        this.syncTask.doTaskTwo();
        this.syncTask.doTaskThree();
    }
}
