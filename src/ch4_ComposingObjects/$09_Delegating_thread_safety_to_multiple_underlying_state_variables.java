package ch4_ComposingObjects;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:51 AM
 */
public class $09_Delegating_thread_safety_to_multiple_underlying_state_variables {

    public class VisualComponent {
        private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
        private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

        public void addKeyListener(KeyListener listener) {
            keyListeners.add(listener);
        }

        public void addMouseListener(MouseListener listener) {
            mouseListeners.add(listener);
        }

        public void removeKeyListener(KeyListener listener) {
            keyListeners.remove(listener);
        }

        public void removeMouseListener(MouseListener listener) {
            mouseListeners.remove(listener);
        }
    }
}
