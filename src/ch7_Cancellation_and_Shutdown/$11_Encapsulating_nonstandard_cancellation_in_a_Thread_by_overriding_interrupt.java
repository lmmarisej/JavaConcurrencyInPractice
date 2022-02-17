package ch7_Cancellation_and_Shutdown;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 7:05 PM
 */
public class $11_Encapsulating_nonstandard_cancellation_in_a_Thread_by_overriding_interrupt {

    public static class ReaderThread extends Thread {
        private static final int BUFSZ = 512;
        private final Socket socket;
        private final InputStream in;

        public ReaderThread(Socket socket) throws IOException {
            this.socket = socket;
            this.in = socket.getInputStream();
        }

        public void interrupt() {
            try {
                socket.close();
            } catch (IOException ignored) {
            } finally {
                super.interrupt();
            }
        }

        public void run() {
            try {
                byte[] buf = new byte[BUFSZ];
                while (true) {
                    int count = in.read(buf);
                    if (count < 0)
                        break;
                    else if (count > 0)
                        processBuffer(buf, count);
                }
            } catch (IOException e) { /* Allow thread to exit */
            }
        }

        public void processBuffer(byte[] buf, int count) {
        }
    }
}
