package com.nbicocchi.exercises.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelPrimes {
    public static class PrimeEngine implements Callable<List<Integer>> {
        int start;
        int end;

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
        callables.add(new PrimeEngine(0, 1000));
        callables.add(new PrimeEngine(1000, 2000));
        callables.add(new PrimeEngine(2000, 3000));
        callables.add(new PrimeEngine(3000, 4000));
        callables.add(new PrimeEngine(4000, 5000));
        callables.add(new PrimeEngine(5000, 6000));

        List<Future<List<Integer>>> futures = executor.invokeAll(callables);

        // main thread does other things
        Thread.sleep(1000);

        for (Future<List<Integer>> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}


