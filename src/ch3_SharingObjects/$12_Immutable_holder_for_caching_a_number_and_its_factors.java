package ch3_SharingObjects;

import net.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 1:07 AM
 */
public class $12_Immutable_holder_for_caching_a_number_and_its_factors {

    @Immutable
    public static class OneValueCache {
        private final BigInteger lastNumber;
        private final BigInteger[] lastFactors;

        public OneValueCache(BigInteger i, BigInteger[] factors) {
            lastNumber = i;
            lastFactors = Arrays.copyOf(factors, factors.length);
        }

        public BigInteger[] getFactors(BigInteger i) {
            if (lastNumber == null || !lastNumber.equals(i))
                return null;
            else
                return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
