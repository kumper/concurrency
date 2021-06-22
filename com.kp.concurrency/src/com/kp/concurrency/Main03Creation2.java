package com.kp.concurrency;

/**
 * @author PZK8WZ
 * @since 15/cze/2021
 */
public class Main03Creation2 {
    public static void main(String[] args) throws InterruptedException {
        final var thread = new NewThread();

        thread.start();

        System.out.println();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
//            this.getPriority()
        }
    }
}
