package ch2_ThreadSafety;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 6:46 PM
 */
public class $07_Code_that_would_deadlock_if_intrinsic_locks_were_not_reentrant {
    class Widget {
        public synchronized void doSomething() {
        }
    }

    class LoggingWidget extends Widget {
        public synchronized void doSomething() {
            System.out.println(toString() + ": calling doSomething");
            super.doSomething();
        }
    }
}
