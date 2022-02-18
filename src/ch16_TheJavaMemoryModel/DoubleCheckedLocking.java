package ch16_TheJavaMemoryModel;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 6:36 PM
 */
@NotThreadSafe
public class DoubleCheckedLocking {
    // 不用volatile，就无法保证Happens-Before，可能发生重排序，将导致不同的线程看到一个残缺的对象
    private static volatile Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    static class Resource {

    }
}