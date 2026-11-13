# E8 — Concurrency

**Java 25 · Practice without AI-generated code**

Implement the exercises yourself and validate your work with the supplied tests where available. The sections follow the order of the covered course topics; exercises may combine earlier skills.

[Course slides and module index](../../../../../../../../../README.md#slides) · [AI exercises](../../../../../../../../exercises-ai/E8%20-%20Java%20Concurrency.md)

## Contents

- [Threads and execution](#threads-and-execution)
- [Shared state and coordination](#shared-state-and-coordination)
- [Executors, tasks and futures](#executors-tasks-and-futures)

## Compiling the exercises

No automated tests are currently supplied for this module. From `exercises/exercises-no-ai/`, compile the exercises with:

```sh
mvn compile
```

## Threads and execution

### Exercise 1 — StartStop

Source: [StartStop.java](StartStop.java). No automated tests are currently supplied for this exercise.

Write a program in which the main thread starts two child threads, waits approximately 100 ms, requests their termination with interrupt(), and waits for them with join(). Each child cooperatively checks for interruption and prints messages like those below, where T1 is its name and the milliseconds indicate elapsed time. Timing, interleaving, and the number of messages are not guaranteed.

```text
T1 started
T1 running since 5 ms
T1 running since 15 ms
T1 running since 17 ms
...
T1 running since 90 ms
T1 running since 100 ms
T1 running since 100 ms
T1 terminated
```

---

### Exercise 2 — DayChangeCheck

Source: [DayChangeCheck.java](DayChangeCheck.java). No automated tests are currently supplied for this exercise.

Write a program in which the main thread starts a child thread that checks approximately every 15 minutes whether the day of the month has changed and prints a message if it has (see Thread.sleep() and java.time.LocalDate). The main thread returns immediately without joining the child. The child is a non-daemon platform thread, so the JVM remains alive after main returns; main returning does not terminate that thread.

---

### Exercise 3 — ThreadPool

Source: [ThreadPool.java](ThreadPool.java). No automated tests are currently supplied for this exercise.

Write a program using a Thread[] to run a fixed number of tasks, one per thread. Each thread prints a start message, sleeps for a randomly chosen duration of less than one second, then prints an end message with its elapsed time. The main thread waits for all tasks with join(). The requested sleep duration is not an upper bound on actual elapsed time. For 8 tasks and 8 threads, the following is one possible output; ordering and timing vary.

```text
START thread=Thread-0 task=0
START thread=Thread-7 task=7
START thread=Thread-6 task=6
START thread=Thread-5 task=5
START thread=Thread-4 task=4
START thread=Thread-3 task=3
START thread=Thread-2 task=2
START thread=Thread-1 task=1
STOP thread=Thread-0 task=0 t=173 ms
STOP thread=Thread-5 task=5 t=255 ms
STOP thread=Thread-3 task=3 t=311 ms
STOP thread=Thread-6 task=6 t=339 ms
STOP thread=Thread-4 task=4 t=436 ms
STOP thread=Thread-2 task=2 t=469 ms
STOP thread=Thread-7 task=7 t=483 ms
STOP thread=Thread-1 task=1 t=522 ms
```

---

## Shared state and coordination

### Exercise 4 — ProducerConsumerUnsafe

Source: [ProducerConsumerUnsafe.java](ProducerConsumerUnsafe.java). No automated tests are currently supplied for this exercise.

Write a producer and a consumer sharing a queue of integers. The main thread starts both, waits approximately 100 ms, requests interruption, and joins them. The producer enqueues integers; the consumer removes them and prints the number of elements consumed before terminating. Use a thread-safe collection, such as ConcurrentLinkedDeque, rather than explicitly synchronizing on the queue. Despite the existing class name, this version must be thread-safe. The count below is illustrative and varies between runs.

```text
Total number of consumed elements: 90065
```

---

### Exercise 5 — ProducerConsumerSafe

Source: [ProducerConsumerSafe.java](ProducerConsumerSafe.java). No automated tests are currently supplied for this exercise.

Write a producer and a consumer sharing a queue of integers. The main thread starts both, waits approximately 100 ms, requests interruption, and joins them. The producer enqueues integers; the consumer removes them and prints the number of elements consumed before terminating. Unlike the previous exercise, use a non-thread-safe queue and protect access with synchronized blocks on the same queue object. Keep the empty-queue check and removal in one synchronized block. The count below is illustrative and varies between runs.

```text
Total number of consumed elements: 90065
```

---

## Executors, tasks and futures

### Exercise 6 — ThreadPoolExecutor

Source: [ThreadPoolExecutor.java](ThreadPoolExecutor.java). No automated tests are currently supplied for this exercise.

ThreadPool.java creates one platform thread per task, which can be costly for large task counts. Write an alternative using a fixed-size ExecutorService to limit the number of worker threads. Shut down the executor and wait for submitted tasks to finish. With 8 tasks and 4 workers, the following is one possible output; ordering and timing vary.

```text
START thread=pool-1-thread-3 task=2
START thread=pool-1-thread-1 task=0
START thread=pool-1-thread-4 task=3
START thread=pool-1-thread-2 task=1
STOP thread=pool-1-thread-3 task=2 t=174 ms
START thread=pool-1-thread-3 task=4
STOP thread=pool-1-thread-1 task=0 t=257 ms
START thread=pool-1-thread-1 task=5
STOP thread=pool-1-thread-4 task=3 t=502 ms
START thread=pool-1-thread-4 task=6
STOP thread=pool-1-thread-1 task=5 t=303 ms
START thread=pool-1-thread-1 task=7
STOP thread=pool-1-thread-2 task=1 t=782 ms
STOP thread=pool-1-thread-3 task=4 t=651 ms
STOP thread=pool-1-thread-4 task=6 t=455 ms
STOP thread=pool-1-thread-1 task=7 t=566 ms
```

---

### Exercise 7 — ScheduledExecution

Source: [ScheduledExecution.java](ScheduledExecution.java). No automated tests are currently supplied for this exercise.

Write a program printing, every second, the time of day followed by "Hello!" as shown below (see java.time.LocalTime, java.util.concurrent.ScheduledExecutorService).

```text
21:03:28.221207: Hello!
21:03:29.198493: Hello!
21:03:30.198768: Hello!
21:03:31.198085: Hello!
21:03:32.198206: Hello!
```

---

### Exercise 8 — ParallelPrimes

Source: [ParallelPrimes.java](ParallelPrimes.java). No automated tests are currently supplied for this exercise.

The following class implements `Callable<List<Integer>>` to return a task result. Use it to search for primes with a fixed-size ExecutorService. Give each task a non-overlapping half-open range, such as [0, 1000), [1000, 2000), and return the primes in that range as a `List<Integer>`. Submit the tasks with invokeAll(), retrieve their results through the returned `List<Future<List<Integer>>>`, and close the executor. Handle InterruptedException and ExecutionException as appropriate.

```java
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

    @Override
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
```

---
