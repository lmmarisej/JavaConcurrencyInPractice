package ch5_BuildingBlocks;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 6:06 PM
 */
public class $20_Factorizing_servlet_that_caches_results_using_Memoizer {

    @ThreadSafe
    public static class Factorizer extends GenericServlet implements Servlet {
        private final Computable<BigInteger, BigInteger[]> c = this::factor;
        private final Computable<BigInteger, BigInteger[]> cache = new Memoizer(c);

        public void service(ServletRequest req, ServletResponse resp) {
            try {
                BigInteger i = extractFromRequest(req);
                encodeIntoResponse(resp, cache.compute(i));
            } catch (InterruptedException e) {
                encodeError(resp, "factorization interrupted");
            }
        }

        void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        }

        void encodeError(ServletResponse resp, String errorString) {
        }

        BigInteger extractFromRequest(ServletRequest req) {
            return new BigInteger("7");
        }

        BigInteger[] factor(BigInteger i) {
            return new BigInteger[]{i}; // Doesn't really factor
        }
    }

    interface Computable<A, V> {
        V compute(A arg) throws InterruptedException;
    }

    public static class Memoizer implements Computable<BigInteger, BigInteger[]> {
        // 缓存计算结果
        private final ConcurrentMap<BigInteger, Future<BigInteger[]>> cache = new ConcurrentHashMap<>();
        private final Computable<BigInteger, BigInteger[]> c;       // 计算逻辑模板

        public Memoizer(Computable<BigInteger, BigInteger[]> c) {
            this.c = c;
        }

        public BigInteger[] compute(final BigInteger arg) throws InterruptedException {
            while (true) {
                Future<BigInteger[]> f = cache.get(arg);
                if (f == null) {
                    Callable<BigInteger[]> eval = () -> c.compute(arg);   // 将参数放入模板，包装为Callable
                    FutureTask<BigInteger[]> ft = new FutureTask<>(eval);
                    f = cache.putIfAbsent(arg, ft);     // 放入缓存
                    if (f == null) {        // 第一次计算
                        f = ft;
                        ft.run();       // 执行计算
                    }
                }
                try {
                    return f.get();     // 获取计算结果
                } catch (CancellationException e) {     // 任务取消
                    cache.remove(arg, f);       // 移除缓存
                } catch (ExecutionException e) {        // 计算出错
                    throw $13_Coercing_an_unchecked_Throwable_to_a_RuntimeException.LaunderThrowable.launderThrowable(e.getCause());
                }
            }
        }
    }
}
