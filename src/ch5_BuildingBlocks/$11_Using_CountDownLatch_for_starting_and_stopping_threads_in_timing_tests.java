package ch5_BuildingBlocks;

import java.util.concurrent.CountDownLatch;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 4:21 PM
 */
public class $11_Using_CountDownLatch_for_starting_and_stopping_threads_in_timing_tests {

    public static class TestHarness {
        public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
            final CountDownLatch startGate = new CountDownLatch(1);         // 起始门
            final CountDownLatch endGate = new CountDownLatch(nThreads);    // 结束门

            for (int i = 0; i < nThreads; i++) {
                Thread t = new Thread(() -> {
                    try {
                        startGate.await();      // 等待其它线程来启动
                        try {
                            task.run();     // 执行线程任务
                        } finally {
                            endGate.countDown();        // 执行结束
                        }
                    } catch (InterruptedException ignored) {
                    }
                });
                t.start();
            }

            long start = System.nanoTime();
            startGate.countDown();      // 任务启动
            endGate.await();        // 等待任务执行结束
            long end = System.nanoTime();
            return end - start;
        }
    }
}
