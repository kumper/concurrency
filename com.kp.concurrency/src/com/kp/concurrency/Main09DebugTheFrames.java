package com.kp.concurrency;

/**
 * Add your description here.
 *
 * @author PZK8WZ
 * @since 17/cze/2021
 */
public class Main09DebugTheFrames {
    public static void main(String[] args) {
        int x = 1; //set breakpoint here and observe how new frame is created on stack (pay attention to variables visibility)
        int y = 2;
        int result = sum(x, y);
    }

    private static int sum(int a, int b) {
        int s = a + b;
        return s;
    }
}
