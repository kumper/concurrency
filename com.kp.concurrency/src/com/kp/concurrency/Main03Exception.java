package com.kp.concurrency;

/**
 * @author PZK8WZ
 * @since 15/cze/2021
 */
public class Main03Exception {
    public static void main(String[] args) throws InterruptedException {
        final var thread = new Thread(() -> {
            System.out.println("We are now in thread " + Thread.currentThread().getName());
            throw new RuntimeException("dummy error thrown");
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
        });

        thread.start();

        Thread.sleep(10000);
    }
}
