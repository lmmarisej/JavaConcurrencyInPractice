package ch5_BuildingBlocks;

import java.util.concurrent.*;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:01 PM
 */
public class $19_Final_implementation_of_Memoizer {

    public static class Memoizer <A, V> implements Computable<A, V> {
        private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
        private final Computable<A, V> c;       // 保存计算逻辑

        public Memoizer(Computable<A, V> c) {
            this.c = c;
        }

        public V compute(final A arg) throws InterruptedException {
            while (true) {
                Future<V> f = cache.get(arg);
                if (f == null) {
                    Callable<V> eval = () -> c.compute(arg);        // 封装计算逻辑为Callable任务
                    FutureTask<V> ft = new FutureTask<>(eval);
                    f = cache.putIfAbsent(arg, ft);
                    if (f == null) {
                        f = ft;
                        ft.run();
                    }
                }
                try {
                    return f.get();
                } catch (CancellationException e) {
                    cache.remove(arg, f);
                } catch (ExecutionException e) {
                    throw $13_Coercing_an_unchecked_Throwable_to_a_RuntimeException.LaunderThrowable.launderThrowable(e.getCause());
                }
            }
        }
    }

    interface Computable<A, V> {
        V compute(A arg) throws InterruptedException;
    }
}
