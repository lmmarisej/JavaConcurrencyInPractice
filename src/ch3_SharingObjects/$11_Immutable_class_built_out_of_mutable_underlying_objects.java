package ch3_SharingObjects;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 1:05 AM
 */
public class $11_Immutable_class_built_out_of_mutable_underlying_objects {

    @Immutable
    public static final class ThreeStooges {
        private final Set<String> stooges = new HashSet<>();

        public ThreeStooges() {
            stooges.add("Moe");
            stooges.add("Larry");
            stooges.add("Curly");
        }

        public boolean isStooge(String name) {
            return stooges.contains(name);
        }

        public String getStoogeNames() {
            List<String> stooges = new Vector<>();
            stooges.add("Moe");
            stooges.add("Larry");
            stooges.add("Curly");
            return stooges.toString();
        }
    }
}
