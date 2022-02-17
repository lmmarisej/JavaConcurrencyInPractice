package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 1:19 AM
 */
public class $15_Class_at_risk_of_failure_if_not_properly_published {

    public class Holder {
        private int n;

        public Holder(int n) {
            this.n = n;
        }

        // 若未进行正确的初始化并构造
        public void assertSanity() {
            if (n != n)
                throw new AssertionError("This statement is false.");
        }
    }
}
