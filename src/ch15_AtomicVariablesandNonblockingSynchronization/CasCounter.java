package ch15_AtomicVariablesandNonblockingSynchronization;

import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 4:57 PM
 */
@ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}