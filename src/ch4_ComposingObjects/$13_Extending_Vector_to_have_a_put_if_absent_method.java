package ch4_ComposingObjects;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:52 AM
 */
public class $13_Extending_Vector_to_have_a_put_if_absent_method {

    @ThreadSafe
    public static class BetterVector <E> extends Vector<E> {
        // When extending a serializable class, you should redefine serialVersionUID
        static final long serialVersionUID = -3963416950630760754L;

        public synchronized boolean putIfAbsent(E x) {
            boolean absent = !contains(x);
            if (absent)
                add(x);
            return absent;
        }
    }
}
