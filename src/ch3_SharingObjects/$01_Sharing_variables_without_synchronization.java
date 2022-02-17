package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 7:19 PM
 */
public class $01_Sharing_variables_without_synchronization {
    public static class NoVisibility {
        private static boolean ready;
        private static int number;

        private static class ReaderThread extends Thread {
            public void run() {
                // 可能会一直循环下去
                while (!ready)      // 无法保证主线程的写入值对读线程是可见的
                    Thread.yield();
                System.out.println(number);
            }
        }

        // 主线程
        public static void main(String[] args) {
            new ReaderThread().start();     // 读线程
            number = 42;
            ready = true;
        }
    }
}
