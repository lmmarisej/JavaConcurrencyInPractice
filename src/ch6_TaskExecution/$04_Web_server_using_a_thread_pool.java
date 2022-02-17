package ch6_TaskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:35 PM
 */
public class $04_Web_server_using_a_thread_pool {

    public static class TaskExecutionWebServer {
        private static final int NTHREADS = 100;
        private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = () -> handleRequest(connection);
                exec.execute(task);     // 将任务提交给线程池
            }
        }

        private static void handleRequest(Socket connection) {      // request-handling logic here
        }
    }
}
