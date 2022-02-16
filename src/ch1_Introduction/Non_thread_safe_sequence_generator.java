package ch1_Introduction;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 4:48 PM
 */
@NotThreadSafe
public class Non_thread_safe_sequence_generator {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}
