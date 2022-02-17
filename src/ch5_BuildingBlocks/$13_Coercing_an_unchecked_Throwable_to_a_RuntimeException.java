package ch5_BuildingBlocks;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 5:11 PM
 */
public class $13_Coercing_an_unchecked_Throwable_to_a_RuntimeException {

    public static class LaunderThrowable {

        // 转换异常
        public static RuntimeException launderThrowable(Throwable t) {
            if (t instanceof RuntimeException)
                return (RuntimeException) t;
            else if (t instanceof Error)
                throw (Error) t;
            else
                throw new IllegalStateException("Not unchecked", t);
        }
    }
}
