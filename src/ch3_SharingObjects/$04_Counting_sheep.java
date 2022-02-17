package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 7:36 PM
 */
public class $04_Counting_sheep {
    public class CountingSheep {
        volatile boolean asleep;        // 写入对其它线程立即可见

        void tryToSleep() {
            while (!asleep)
                countSomeSheep();
        }

        void countSomeSheep() {
            // One, two, three...
        }
    }
}
