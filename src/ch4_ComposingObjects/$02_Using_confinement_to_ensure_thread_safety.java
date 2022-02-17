package ch4_ComposingObjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:50 AM
 */
public class $02_Using_confinement_to_ensure_thread_safety {

    @ThreadSafe
    public static class PersonSet {
        @GuardedBy("this")
        private final Set<Person> mySet = new HashSet<>();

        public synchronized void addPerson(Person p) {
            mySet.add(p);
        }

        public synchronized boolean containsPerson(Person p) {
            return mySet.contains(p);
        }

        interface Person {
        }
    }
}
