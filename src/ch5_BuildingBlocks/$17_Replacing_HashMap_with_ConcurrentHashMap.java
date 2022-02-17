package ch5_BuildingBlocks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 5:51 PM
 */
public class $17_Replacing_HashMap_with_ConcurrentHashMap {

    public class Memoizer2 <A, V> implements Computable<A, V> {
        private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
        private final Computable<A, V> c;

        public Memoizer2(Computable<A, V> c) {
            this.c = c;
        }

        public V compute(A arg) throws InterruptedException {
            V result = cache.get(arg);
            if (result == null) {
                result = c.compute(arg);
                cache.put(arg, result);
            }
            return result;
        }
    }

    interface Computable<A, V> {
        V compute(A arg) throws InterruptedException;
    }
}
