package com.nbicocchi.exercises.warmup;

import java.util.random.RandomGenerator;

public class EstimatePI {

    public static long areInside(long trials) {
        final RandomGenerator RND = RandomGenerator.getDefault();
        long areInside = 0;

        for (int i = 0; i < trials; i++) {
            double x = RND.nextDouble();
            double y = RND.nextDouble();

            if (x * x + y * y < 1) {
                areInside++;
            }
        }

        return areInside;
    }

    public static void main(String[] args) {
        for (int i = 3; i <= 24; i++) {
            long trials = (long)Math.pow(2, i);
            long areInside = areInside(trials);

            double estimate = 4.0 * areInside / (double)trials;
            double error = 100.0 * (estimate - Math.PI) / Math.PI;
            System.out.printf("[trials=2^%d] [error=%.8fpc] [estimate=%.16f]\n", i, error, estimate);
        }
    }
}
