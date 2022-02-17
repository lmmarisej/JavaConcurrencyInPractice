package ch3_SharingObjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 7:33 PM
 */
public class $03_Thread_safe_mutable_integer_holder {
    @ThreadSafe
    public class SynchronizedInteger {
        @GuardedBy("this") private int value;

        public synchronized int get() {
            return value;
        }

        public synchronized void set(int value) {
            this.value = value;
        }
    }
}
