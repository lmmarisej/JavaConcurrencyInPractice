package ch14_BuildingCustomSynchronizers;

import net.jcip.annotations.ThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/18 3:00 PM
 */
@ThreadSafe
public class GrumpyBoundedBuffer <V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer() {
        this(100);
    }

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull())
            throw new BufferFullException();
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }
}

class ExampleUsage {
    private GrumpyBoundedBuffer<String> buffer;
    int SLEEP_GRANULARITY = 50;

    void useBuffer() throws InterruptedException {
        while (true) {
            try {
                String item = buffer.take();
                // use item
                break;
            } catch (BufferEmptyException e) {
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
    }
}

class BufferFullException extends RuntimeException {
}

class BufferEmptyException extends RuntimeException {
}