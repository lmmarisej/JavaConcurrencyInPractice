package ch15_AtomicVariablesandNonblockingSynchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 5:06 PM
 */
@ThreadSafe
public class ReentrantLockPseudoRandom  {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            seed = calculateNext(s);
            int remainder = s % n;
            return remainder > 0 ? remainder : remainder + n;
        } finally {
            lock.unlock();
        }
    }

    private int calculateNext(int s) {
        return 0;
    }
}