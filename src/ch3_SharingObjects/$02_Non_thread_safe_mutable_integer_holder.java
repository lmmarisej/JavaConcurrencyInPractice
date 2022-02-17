package ch3_SharingObjects;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 7:21 PM
 */
public class $02_Non_thread_safe_mutable_integer_holder {
    @NotThreadSafe
    public class MutableInteger {
        private int value;

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }
    }
}
