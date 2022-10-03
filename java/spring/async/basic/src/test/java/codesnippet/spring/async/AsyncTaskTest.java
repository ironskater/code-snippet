package codesnippet.spring.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTaskTest {

    private Task asyncTask;

    @Autowired
    public AsyncTaskTest(Task asyncTask) {
        this.asyncTask = asyncTask;
    }

    @Test
    public void test() throws Exception {
        this.asyncTask.doTaskOne();
        this.asyncTask.doTaskTwo();
        this.asyncTask.doTaskThree();
    }
}
