package ch4_ComposingObjects;

import net.jcip.annotations.GuardedBy;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:50 AM
 */
public class $03_Guarding_state_with_a_private_lock {

    public static class PrivateLock {
        private final Object myLock = new Object();
        @GuardedBy("myLock")
        Widget widget;

        void someMethod() {
            synchronized (myLock) {
                // Access or modify the state of widget
            }
        }
    }

    static class Widget {
    }
}
