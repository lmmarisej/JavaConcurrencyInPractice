package ch5_BuildingBlocks;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 2:46 PM
 */
public class $06_Iteration_hidden_within_string_concatenation {

    public static class HiddenIterator {
        @GuardedBy("this") private final Set<Integer> set = new HashSet<>();

        public synchronized void add(Integer i) {
            set.add(i);
        }

        public synchronized void remove(Integer i) {
            set.remove(i);
        }

        public void addTenThings() {
            Random r = new Random();
            for (int i = 0; i < 10; i++)
                add(r.nextInt());       // 线程安全
            System.out.println("DEBUG: added ten elements to " + set);
        }
    }
}
