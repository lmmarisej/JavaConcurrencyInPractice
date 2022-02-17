package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 9:56 PM
 */
public class $07_Implicitly_allowing_the_this_reference_to_escape {
    public static class ThisEscape {
        public ThisEscape(EventSource source) {
            // 在发布EventListener的时候，也隐含地发布了ThisEscape实例本身
            // source.registerListener(this::doSomething);
            source.registerListener(new EventListener() {
                public void onEvent(Event e) {
                    ThisEscape.this.doSomething(e);
                }
            });
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
