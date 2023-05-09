package com.nbicocchi.exercises.warmup;

public class EstimatePI {
    public static void main(String[] args) {
        final int BATCH_SIZE = 1000000;
        final int LOG_SIZE = 25000;
        int trials = 0;
        int inCircleTrials = 0;

        for (int i = 0; i < BATCH_SIZE; i++) {
            double x = Math.random();
            double y = Math.random();

            trials++;

            if (x * x + y * y < 1) {
                inCircleTrials++;
            }

            if (trials % LOG_SIZE == 0) {
                double estimate = 4 * inCircleTrials/(double)trials;
                double error = 100 * (estimate - Math.PI) / Math.PI;
                System.out.printf("[trial=%d] [error=%fpc] [estimate=%.16f]\n", trials, error, estimate);
            }
        }
    }
}
