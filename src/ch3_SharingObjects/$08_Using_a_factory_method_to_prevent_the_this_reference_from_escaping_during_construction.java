package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 10:15 PM
 */
public class $08_Using_a_factory_method_to_prevent_the_this_reference_from_escaping_during_construction {

    public static class SafeListener {
        private final EventListener listener;

        private SafeListener() {        // 私有构造，避免将SafeListener.this暴露给EventListener对象
            listener = this::doSomething;       // 成员变量初始化
        }

        public static SafeListener newInstance(EventSource source) {
            SafeListener safe = new SafeListener();
            source.registerListener(safe.listener);
            return safe;
        }

        void doSomething(Event e) {
        }


        interface EventSource {
            void registerListener(EventListener e);
        }

        interface EventListener {
            void onEvent(Event e);
        }

        interface Event {
        }
    }

}
