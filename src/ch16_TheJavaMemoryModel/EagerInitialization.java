package ch16_TheJavaMemoryModel;

import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 6:35 PM
 */
@ThreadSafe
public class EagerInitialization {
    private static final Resource resource = new Resource();        // 由JVM线程安全的加载机制来保证

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}