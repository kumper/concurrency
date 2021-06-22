package com.kp.concurrency;

/**
 * Add your description here.
 *
 * @author PZK8WZ
 * @since 17/cze/2021
 */
public class Main10RaceCondition {
    public static void main(String[] args) throws InterruptedException {

        final var inventoryCounter = new InventoryCounter();
        final var incrementingThread = new IncrementingThread(inventoryCounter);
        final var decrementingThread = new DecrementingThread(inventoryCounter);

//        //good
//        incrementingThread.start();
//        incrementingThread.join();
//        decrementingThread.start();
//        decrementingThread.join();

        //bad
        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        Object lock = new Object();
        Object lock1 = new Object();
        Object lock2 = new Object();

        //non thread-safe
//        public void increment() {
//                this.items++;
//        }
//
//        public void decrement() {
//                this.items--;
//        }

        public synchronized void increment() {
                this.items++;
        }

        public synchronized void decrement() {
                this.items--;
        }

//        public void increment() {
//            synchronized (this) {
//                this.items++;
//            }
//        }
//
//        public void decrement() {
//            synchronized (this) {
//                this.items--;
//            }
//        }
//
//        public void increment() {
//            synchronized (lock) {
//                this.items++;
//            }
//        }
//
//        public void decrement() {
//            synchronized (lock) {
//                this.items--;
//            }
//        }

//        //two separate lock objects will allow two threads working in parallel, but here again we face race conditions
//        //so this is wrong!
//        public synchronized void increment() {
//            synchronized (lock1) {
//                this.items++;
//            }
//        }
//
//        public synchronized void decrement() {
//            synchronized (lock2) {
//                this.items--;
//            }
//        }

        public int getItems() {
            return items;
        }
    }

}
