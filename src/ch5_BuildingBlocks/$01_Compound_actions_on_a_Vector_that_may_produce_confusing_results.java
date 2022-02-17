package ch5_BuildingBlocks;

import java.util.Vector;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 2:40 PM
 */
public class $01_Compound_actions_on_a_Vector_that_may_produce_confusing_results {

    public static class UnsafeVectorHelpers {
        public static Object getLast(Vector list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }

        public static void deleteLast(Vector list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
