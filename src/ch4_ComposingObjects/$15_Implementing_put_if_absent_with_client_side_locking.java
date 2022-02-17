package ch4_ComposingObjects;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:53 AM
 */
public class $15_Implementing_put_if_absent_with_client_side_locking {

    @NotThreadSafe
    class BadListHelper <E> {
        public List<E> list = Collections.synchronizedList(new ArrayList<>());

        public synchronized boolean putIfAbsent(E x) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }

    @ThreadSafe
    class GoodListHelper <E> {
        public List<E> list = Collections.synchronizedList(new ArrayList<>());

        public boolean putIfAbsent(E x) {
            synchronized (list) {
                boolean absent = !list.contains(x);
                if (absent)
                    list.add(x);
                return absent;
            }
        }
    }
}
