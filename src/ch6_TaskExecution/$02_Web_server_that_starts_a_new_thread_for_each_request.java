package ch6_TaskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:31 PM
 */
public class $02_Web_server_that_starts_a_new_thread_for_each_request {

    public static class ThreadPerTaskWebServer {
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = () -> handleRequest(connection);
                new Thread(task).start();       // 每个请求一个线程
            }
        }

        private static void handleRequest(Socket connection) {
            // request-handling logic here
        }
    }
}
