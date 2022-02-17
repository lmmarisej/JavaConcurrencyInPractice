package ch5_BuildingBlocks;

import java.util.concurrent.BlockingQueue;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 4:21 PM
 */
public class $10_Restoring_the_interrupted_status_so_as_not_to_swallow_the_interrupt {

    public static class TaskRunnable implements Runnable {
        BlockingQueue<Task> queue;

        public void run() {
            try {
                processTask(queue.take());
            } catch (InterruptedException e) {
                // restore interrupted status
                Thread.currentThread().interrupt();     // 继续上抛中断
            }
        }

        void processTask(Task task) {
            // Handle the task
        }

        interface Task {
        }
    }
}
