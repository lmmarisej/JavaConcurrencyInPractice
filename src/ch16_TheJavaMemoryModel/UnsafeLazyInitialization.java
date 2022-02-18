package ch16_TheJavaMemoryModel;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 6:34 PM
 */
@NotThreadSafe
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }

    static class Resource {
    }
}