package ch7_Cancellation_and_Shutdown;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 8:41 PM
 */
public class $25_UncaughtExceptionHandler_that_logs_the_exception {

    public static class UEHLogger implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
        }
    }
}
