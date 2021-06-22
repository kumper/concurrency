package com.kp.concurrency;

import java.util.Random;

/**
 * Add your description here.
 *
 * @author PZK8WZ
 * @since 18/cze/2021
 */
public class Main11Metrics {

    public static void main(String[] args) {
        final var metrics = new Metrics();
        final var businessLogic1 = new BusinessLogic(metrics);
        final var businessLogic2 = new BusinessLogic(metrics);
        final var metricsPrinter = new MetricsPrinter(metrics);

        businessLogic1.start();
        businessLogic2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                final var currentAverage = metrics.getAverage();
                System.out.println("Current Average is: " + currentAverage);
            }
        }
    }


    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                final var start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }

                final var stop = System.currentTimeMillis();
                metrics.addSample(stop - start);
            }
        }
    }

    public static class Metrics {

        private long count = 0;
        private volatile double average = 0.0; //read/write from/to long and double is non-atomic, volatile do the job; other primitives write/read are atomic (references to objects too)

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
