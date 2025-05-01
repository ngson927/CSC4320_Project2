import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BoundedBuffer is our shared space where producers drop off items
 * and consumers come pick them up. We're using semaphores and a lock
 * to keep everything from turning into a chaotic mess.
 */
public class BoundedBuffer {
    // The buffer is just an array to hold our items
    private final int[] buffer;

    // 'in' is where the next item gets added
    // 'out' is where the next item gets taken from
    private int in = 0, out = 0;

    // Semaphore to track how many items are in the buffer
    private final Semaphore full;

    // Semaphore to track how many empty spots are left
    private final Semaphore empty;

    // Lock to make sure only one thread is messing with the buffer at a time
    private final ReentrantLock mutex;

    /**
     * Sets everything up — creates the buffer, sets how many spots are empty,
     * and gets our semaphores and lock ready to go.
     *
     * @param size how big the buffer should be
     */
    public BoundedBuffer(int size) {
        buffer = new int[size];
        full = new Semaphore(0);          // Nothing in the buffer yet
        empty = new Semaphore(size);      // All spots are open at the start
        mutex = new ReentrantLock();      // Only one producer or consumer at a time please
    }

    /**
     * Producers call this to add an item to the buffer.
     * If the buffer is full, it’ll wait until there’s space.
     *
     * @param item the thing we're adding to the buffer
     * @param pid  which producer is doing this (just for printing)
     * @throws InterruptedException if something interrupts the thread while it's waiting
     */
    public void produce(int item, int pid) throws InterruptedException {
        empty.acquire();                 // Wait here if there's no room in the buffer
        mutex.lock();                    // Only one person at a time in this critical section

        buffer[in] = item;               // Drop the item in the next available spot
        System.out.println("[Producer " + pid + "] Produced " + item);

        // Move to the next spot, wrapping around if we hit the end
        in = (in + 1) % buffer.length;

        mutex.unlock();                  // Done editing the buffer, let others in
        full.release();                  // One more item is now available to consume
    }

    /**
     * Consumers call this to take an item from the buffer.
     * If the buffer is empty, they’ll wait until something shows up.
     *
     * @param pid which consumer is doing this (again, for printing)
     * @return the item that was taken out of the buffer
     * @throws InterruptedException if something interrupts the thread while it's waiting
     */
    public int consume(int pid) throws InterruptedException {
        full.acquire();                 // Wait here if there’s nothing to consume
        mutex.lock();                   // Lock it down so no one else messes with the buffer

        int item = buffer[out];         // Grab the item at
