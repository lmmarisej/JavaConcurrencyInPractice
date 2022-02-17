package ch7_Cancellation_and_Shutdown;

import java.util.concurrent.*;

import static ch5_BuildingBlocks.$13_Coercing_an_unchecked_Throwable_to_a_RuntimeException.LaunderThrowable.launderThrowable;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:56 PM
 */
public class $10_Cancelling_a_task_using_Future {

    public static class TimedRun {
        private static final ExecutorService taskExec = Executors.newCachedThreadPool();

        public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
            Future<?> task = taskExec.submit(r);
            try {
                task.get(timeout, unit);
            } catch (TimeoutException e) {
                // task will be cancelled below
            } catch (ExecutionException e) {
                // exception thrown in task; rethrow
                throw launderThrowable(e.getCause());
            } finally {
                // Harmless if task already completed
                task.cancel(true); // interrupt if running
            }
        }
    }
}
