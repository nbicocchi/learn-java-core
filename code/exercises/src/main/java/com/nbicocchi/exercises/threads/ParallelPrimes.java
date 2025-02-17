package com.nbicocchi.exercises.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelPrimes {
    public static class PrimeEngine implements Callable<List<Integer>> {
        final int start;
        final int end;

        public PrimeEngine(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public List<Integer> call() {
            List<Integer> result = new ArrayList<>();
            for (int i = start; i < end; i++) {
                if (isPrime(i)) {
                    result.add(i);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Callable<List<Integer>>> callables = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            callables.add(new PrimeEngine(i * 1000, (i + 1) * 1000));
        }

        List<Future<List<Integer>>> futures = executor.invokeAll(callables);

        // main thread does other things
        Thread.sleep(1000);

        for (Future<List<Integer>> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}


