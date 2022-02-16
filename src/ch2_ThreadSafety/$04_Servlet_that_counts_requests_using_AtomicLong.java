package ch2_ThreadSafety;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 6:11 PM
 */
@ThreadSafe
public class $04_Servlet_that_counts_requests_using_AtomicLong {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }

    BigInteger[] factor(BigInteger i) {
        return null;
    }
}
