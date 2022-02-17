package ch3_SharingObjects;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 1:12 AM
 */
public class $13_Caching_the_last_result_using_a_volatile_reference_to_an_immutable_holder_object {

    @ThreadSafe
    public class VolatileCachedFactorizer extends GenericServlet implements Servlet {
        private volatile $12_Immutable_holder_for_caching_a_number_and_its_factors
                .OneValueCache cache = new $12_Immutable_holder_for_caching_a_number_and_its_factors
                .OneValueCache(null, null);

        public void service(ServletRequest req, ServletResponse resp) {
            BigInteger i = extractFromRequest(req);
            BigInteger[] factors = cache.getFactors(i);
            if (factors == null) {
                factors = factor(i);
                cache = new $12_Immutable_holder_for_caching_a_number_and_its_factors.OneValueCache(i, factors);
            }
            encodeIntoResponse(resp, factors);
        }

        void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        }

        BigInteger extractFromRequest(ServletRequest req) {
            return new BigInteger("7");
        }

        BigInteger[] factor(BigInteger i) {
            // Doesn't really factor
            return new BigInteger[]{i};
        }
    }

}
