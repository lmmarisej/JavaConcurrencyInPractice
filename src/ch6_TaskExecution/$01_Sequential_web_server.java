package ch6_TaskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:24 PM
 */
public class $01_Sequential_web_server {

    public static class SingleThreadWebServer {

        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while (true) {
                Socket connection = socket.accept();
                handleRequest(connection);      // 串行执行
            }
        }

        private static void handleRequest(Socket connection) {
            // request-handling logic here
        }
    }
}
