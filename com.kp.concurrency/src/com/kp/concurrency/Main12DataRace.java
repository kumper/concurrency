package com.kp.concurrency;

/**
 * Add your description here.
 *
 * @author PZK8WZ
 * @since 17/cze/2021
 */
public class Main12DataRace {
    public static void main(String[] args) throws InterruptedException {
        final var sharedClass = new SharedClass();
        final var thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });

        final var thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkFOrDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass {
//        private int x = 0;
//        private int y = 0; //here processor can run instructions out of order, keeping them logically correct, but will not work in multi-threaded (multi cores)
        private volatile int x = 0;
        private volatile int y = 0; //or we can make the methods synchronized

        public void increment() {
            x++;
            y++;
        }

        public void checkFOrDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }

}
