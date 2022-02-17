package ch7_Cancellation_and_Shutdown;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 8:35 PM
 */
public class $21_ExecutorService_that_keeps_track_of_cancelled_tasks_after_shutdown {

    public static class TrackingExecutor extends AbstractExecutorService {
        private final ExecutorService exec;
        private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());

        public TrackingExecutor(ExecutorService exec) {
            this.exec = exec;
        }

        public void shutdown() {
            exec.shutdown();
        }

        public List<Runnable> shutdownNow() {
            return exec.shutdownNow();
        }

        public boolean isShutdown() {
            return exec.isShutdown();
        }

        public boolean isTerminated() {
            return exec.isTerminated();
        }

        public boolean awaitTermination(long timeout, TimeUnit unit)
                throws InterruptedException {
            return exec.awaitTermination(timeout, unit);
        }

        public List<Runnable> getCancelledTasks() {
            if (!exec.isTerminated())
                throw new IllegalStateException(/*...*/);
            return new ArrayList<>(tasksCancelledAtShutdown);
        }

        public void execute(final Runnable runnable) {
            exec.execute(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        if (isShutdown() && Thread.currentThread().isInterrupted())
                            tasksCancelledAtShutdown.add(runnable);
                    }
                }
            });
        }
    }
}
