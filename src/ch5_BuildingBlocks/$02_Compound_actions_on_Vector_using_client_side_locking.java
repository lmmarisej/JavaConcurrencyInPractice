package ch5_BuildingBlocks;

import java.util.Vector;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 2:44 PM
 */
public class $02_Compound_actions_on_Vector_using_client_side_locking {

    public static class SafeVectorHelpers {
        public static Object getLast(Vector list) {
            synchronized (list) {       // 容器虽然线程安全，但是在进行复合操作时也需要在客户端进行加锁
                int lastIndex = list.size() - 1;
                return list.get(lastIndex);
            }
        }

        public static void deleteLast(Vector list) {
            synchronized (list) {
                int lastIndex = list.size() - 1;
                list.remove(lastIndex);
            }
        }
    }
}
