package ch16_TheJavaMemoryModel;

import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 6:35 PM
 */
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        private final static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceFactory.ResourceHolder.resource;
    }

    static class Resource {
    }
}