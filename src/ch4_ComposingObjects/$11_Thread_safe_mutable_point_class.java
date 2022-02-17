package ch4_ComposingObjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:52 AM
 */
public class $11_Thread_safe_mutable_point_class {

    @ThreadSafe
    public static class SafePoint {
        @GuardedBy("this") private int x, y;

        private SafePoint(int[] a) {
            this(a[0], a[1]);
        }

        public SafePoint(SafePoint p) {
            this(p.get());
        }

        public SafePoint(int x, int y) {
            this.set(x, y);
        }

        public synchronized int[] get() {
            return new int[]{x, y};
        }

        public synchronized void set(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
