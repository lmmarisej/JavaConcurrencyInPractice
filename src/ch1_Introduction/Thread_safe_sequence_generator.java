package ch1_Introduction;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 4:50 PM
 */
@ThreadSafe
public class Thread_safe_sequence_generator {
    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}