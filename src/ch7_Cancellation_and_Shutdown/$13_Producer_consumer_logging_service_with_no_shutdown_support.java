package ch7_Cancellation_and_Shutdown;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 8:16 PM
 */
public class $13_Producer_consumer_logging_service_with_no_shutdown_support {

    public static class LogWriter {
        private final BlockingQueue<String> queue;
        private final LoggerThread logger;
        private static final int CAPACITY = 1000;

        public LogWriter(Writer writer) {
            this.queue = new LinkedBlockingQueue<>(CAPACITY);
            this.logger = new LoggerThread(writer);
        }

        public void start() {
            logger.start();
        }

        public void log(String msg) throws InterruptedException {
            queue.put(msg);         // 可被多线程写入
        }

        private class LoggerThread extends Thread {
            private final PrintWriter writer;

            public LoggerThread(Writer writer) {
                this.writer = new PrintWriter(writer, true); // autoflush
            }

            public void run() {
                try {
                    while (true)
                        writer.println(queue.take());       // 单线程消费
                } catch (InterruptedException ignored) {
                } finally {
                    writer.close();
                }
            }
        }
    }
}
