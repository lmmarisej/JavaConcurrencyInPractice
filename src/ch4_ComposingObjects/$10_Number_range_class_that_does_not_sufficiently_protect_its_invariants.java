package ch4_ComposingObjects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:51 AM
 */
public class $10_Number_range_class_that_does_not_sufficiently_protect_its_invariants {

    public class NumberRange {
        // INVARIANT: lower <= upper
        private final AtomicInteger lower = new AtomicInteger(0);
        private final AtomicInteger upper = new AtomicInteger(0);

        public void setLower(int i) {
            // Warning -- unsafe check-then-act
            if (i > upper.get())
                throw new IllegalArgumentException("can't set lower to " + i + " > upper");
            lower.set(i);
        }

        public void setUpper(int i) {
            // Warning -- unsafe check-then-act
            if (i < lower.get())
                throw new IllegalArgumentException("can't set upper to " + i + " < lower");
            upper.set(i);
        }

        public boolean isInRange(int i) {
            return (i >= lower.get() && i <= upper.get());
        }
    }

}
