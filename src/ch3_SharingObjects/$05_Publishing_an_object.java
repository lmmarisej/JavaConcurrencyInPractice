package ch3_SharingObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 9:24 PM
 */
public class $05_Publishing_an_object {
    static class Secrets {
        public static Set<Secret> knownSecrets;

        public void initialize() {
            knownSecrets = new HashSet<Secret>();       // 实例方法创建的对象被引用到静态字段中
        }
    }


    static class Secret {
    }
}
