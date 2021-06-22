package com.kp.concurrency;

import java.math.BigInteger;

public class Main08ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calculateResult(BigInteger.valueOf(3), BigInteger.valueOf(3), BigInteger.valueOf(3), BigInteger.valueOf(3)));
    }
    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        if (base1.compareTo(BigInteger.ZERO) < 0 || power1.compareTo(BigInteger.ZERO) < 0 || base2.compareTo(BigInteger.ZERO) < 0 || power2.compareTo(BigInteger.ZERO) < 0) {
            return BigInteger.ZERO;
        }
        PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        result = t1.getResult().add(t2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}