public class SyncDemo {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(3);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.produce(i, 1);
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.consume(1);
                    Thread.sleep(800);
                } catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}

