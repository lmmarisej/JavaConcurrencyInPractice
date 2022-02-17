package ch4_ComposingObjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:47 AM
 */
public class $01_Simple_thread_safe_counter_using_the_Java_monitor_pattern {

    @ThreadSafe
    public static final class Counter {
        @GuardedBy("this") private long value = 0;

        public synchronized long getValue() {
            return value;
        }

        public synchronized long increment() {
            if (value == Long.MAX_VALUE)
                throw new IllegalStateException("counter overflow");
            return ++value;
        }
    }
}
