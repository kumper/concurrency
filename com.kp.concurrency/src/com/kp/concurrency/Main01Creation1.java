package com.kp.concurrency;

/**
 * @author PZK8WZ
 * @since 15/cze/2021
 */
public class Main01Creation1 {
    public static void main(String[] args) throws InterruptedException {
        final var thread = new Thread(() -> {
            System.out.println("We are now in thread " + Thread.currentThread().getName());
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
        });

        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.setPriority(Thread.MIN_PRIORITY);
//        thread.setPriority(Thread.NORM_PRIORITY); //default

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);
    }
}
