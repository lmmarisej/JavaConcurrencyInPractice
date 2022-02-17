package ch6_TaskExecution;

import java.util.concurrent.Executor;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:38 PM
 */
public class $05_Executor_that_starts_a_new_thread_for_each_task {

    public class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) {
            new Thread(r).start();
        };
    }
}
