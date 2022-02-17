package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 1:14 AM
 */
public class $14_Publishing_an_object_without_adequate_synchronization {

    public static class StuffIntoPublic {
        public Holder holder;

        public void initialize() {
            holder = new Holder(42);
        }
    }

    public static class Holder {
        public Holder(int i) {
        }
    }
}
