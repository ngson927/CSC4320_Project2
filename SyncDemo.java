/**
 * SyncDemo sets up a basic test to show how our BoundedBuffer works.
 * We’ve got one producer and one consumer going back and forth.
 */
public class SyncDemo {
    public static void main(String[] args) {
        // Create a buffer with 3 slots — this is the shared space
        BoundedBuffer buffer = new BoundedBuffer(3);

        // Producer thread: makes 5 items and adds them to the buffer
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.produce(i, 1);  // Produce item i, label producer as "1"
                    Thread.sleep(500);     // Short pause before making the next one
                } catch (InterruptedException e) {
                    // If something interrupts the thread, we’ll just move on silently
                }
            }
        });

        // Consumer thread: takes 5 items out of the buffer
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.consume(1);     // Consume an item, label consumer as "1"
                    Thread.sleep(800);     // Slightly slower consumer
                } catch (InterruptedException e) {
                    // Also silently ignore interruptions here
                }
            }
        });

        // Start both threads — let the back-and-forth begin
        producer.start();
        consumer.start();
    }
}
