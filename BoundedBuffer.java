import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final int[] buffer;
    private int in = 0, out = 0;
    private final Semaphore full, empty;
    private final ReentrantLock mutex;

    public BoundedBuffer(int size) {
        buffer = new int[size];
        full = new Semaphore(0);
        empty = new Semaphore(size);
        mutex = new ReentrantLock();
    }

    public void produce(int item, int pid) throws InterruptedException {
        empty.acquire();
        mutex.lock();
        buffer[in] = item;
        System.out.println("[Producer " + pid + "] Produced " + item);
        in = (in + 1) % buffer.length;
        mutex.unlock();
        full.release();
    }

    public int consume(int pid) throws InterruptedException {
        full.acquire();
        mutex.lock();
        int item = buffer[out];
        System.out.println("[Consumer " + pid + "] Consumed " + item);
        out = (out + 1) % buffer.length;
        mutex.unlock();
        empty.release();
        return item;
    }
}
