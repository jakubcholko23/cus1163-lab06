package javapro;

import java.util.ArrayList;

public class ProducerConsumerLab {

    /**
     * SharedBuffer - Thread-safe bounded buffer (FULLY PROVIDED)
     */
    static class SharedBuffer {
        private final ArrayList<Integer> buffer;
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.buffer = new ArrayList<>();
            this.capacity = capacity;
            System.out.println("Buffer created with capacity: " + capacity);
        }

        public synchronized void produce(int value) throws InterruptedException {
            while (buffer.size() >= capacity) {
                System.out.println("[Producer] Buffer FULL - waiting...");
                wait();
            }
            buffer.add(value);
            System.out.println("[Producer] Produced: " + value + " | Buffer: " + buffer);
            notifyAll();
        }

        public synchronized int consume() throws InterruptedException {
            while (buffer.isEmpty()) {
                System.out.println("[Consumer] Buffer EMPTY - waiting...");
                wait();
            }
            int value = buffer.remove(0);
            System.out.println("[Consumer] Consumed: " + value + " | Buffer: " + buffer);
            notifyAll();
            return value;
        }
    }

    /**
     * TODO 1: Implement Producer class
     * Create a class that implements Runnable and:
     * 1. Has a private SharedBuffer field
     * 2. Has a constructor that accepts SharedBuffer parameter
     * 3. In run() method:
     *    - Use try-catch for InterruptedException
     *    - Loop 10 times (i from 0 to 9)
     *    - Call buffer.produce(i) each iteration
     *    - Print "[Producer] finished producing 10 items" when done
     *    - In catch block, print "[Producer] was interrupted"
     */
    static class Producer implements Runnable {
        // TODO 1: Implement Producer class here
        // Step 1: Add private SharedBuffer field

        // Step 2: Add constructor
        public Producer(SharedBuffer buffer) {
            // Initialize the buffer field
        }

        // Step 3: Implement run() method
        @Override
        public void run() {
            // Add your implementation here
        }
    }

    /**
     * TODO 2: Implement Consumer class
     */
    static class Consumer implements Runnable {
        // TODO 2: Implement Consumer class here
        // Step 1: Add private SharedBuffer field

        // Step 2: Add constructor
        public Consumer(SharedBuffer buffer) {
            // Initialize the buffer field
        }

        // Step 3: Implement run() method
        @Override
        public void run() {
            // Add your implementation here
        }
    }

    /**
     * Main method (FULLY PROVIDED)
     */
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        System.out.println();
        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
            return;
        }

        System.out.println("\nAll threads completed successfully!");
    }
}
