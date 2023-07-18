package codesnippet.spring.playground;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * REF: https://juejin.cn/post/6844903621822251021
 */
@SpringBootApplication
@EnableAsync
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ThreadPoolTaskExecutor testExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(10000);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setThreadNamePrefix("push-log-inser-dbt-task-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    CommandLineRunner execute(ThreadPoolTaskExecutor testExecutor) {

        return args -> {
            testExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }

                System.out.println("execute 1");
            });

            testExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {

                    }

                    System.out.println("execute 2");
                    testExecutor.shutdown();
                }
            });

            System.out.println(args[0]);
            System.out.println(args[1]);
        };
    }
}
