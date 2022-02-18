package ch15_AtomicVariablesandNonblockingSynchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 5:07 PM
 */
@ThreadSafe
public class AtomicPseudoRandom  {
    private AtomicInteger seed;

    AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = calculateNext(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }

    private int calculateNext(int s) {
        return 0;
    }
}