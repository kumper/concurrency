package com.kp.concurrency;

import java.math.BigInteger;

/**
 * @author PZK8WZ
 * @since 15/cze/2021
 */
public class Main06DaemonThread {
    public static void main(String[] args) throws InterruptedException {
//        final var thread = new Thread(new LongComputationTask(BigInteger.valueOf(2), BigInteger.valueOf(10)));
        final var thread = new Thread(new LongComputationTask(BigInteger.valueOf(200000), BigInteger.valueOf(1000000000)));

        //deamon thread is not main focus of application or runs code we don't really control
        //deamon thread will not prevent application to stop when main thread wants to exit
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(100);
        thread.interrupt(); //does not work
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            var result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }

}
