# Object-Oriented Programming - DIEF/UNIMORE

## Java Exercises (Threads)

**[StartStop.java]** Write a program in which the main thread starts two child threads, waits 100 ms, and gracefully terminates both of them. Each child thread, during its lifespan, prints the following messages where *T1* is its name and the milliseconds represent the time elapsed since the thread first started.

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

**[DayChangeCheck.java]** Write a program in which the main thread starts a child thread which, every 15 minutes, checks if the day of the month has changed and eventually prints a message (see Thread.sleep(), java.time.LocalDate). The main thread do not wait, but terminates immediately after starting the child thread (bad practice! the main thread should always wait for its children).

**[ThreadPool.java]** Write a program using a Thread[] (an array of Thread objects) for running a fixed number of tasks using the same number of parallel threads. Each thread, prints a message when it starts, sleeps for a random amount of time (< 1 sec) and prints another message when it terminates showing the total amount of time it ran. For example, with 8 tasks (implying 8 threads), an output similar to the one shown below is expected.

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

**[ThreadPoolExecutor.java]** The exercise proposed in ThreadPool.java suffers a relevant issue. In case of a high number (>> number of physical cores) of tasks, a high number of parallel threads is launched. This practice is not always optimal. Write an alternative version of ThreadPool.java making use of an ExecutorService (see java.util.concurrent.ExecutorService) limiting the number of concurrent threads to a specified amount. For example, with 8 tasks and 4 parallel threads, an output similar to the one shown below is expected.

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

**[ScheduledExecution.java]** Write a program printing, every second, the time of day followed by "Hello!" as shown below (see java.time.LocalTime, java.util.concurrent.ScheduledExecutorService).

```text
21:03:28.221207: Hello!
21:03:29.198493: Hello!
21:03:30.198768: Hello!
21:03:31.198085: Hello!
21:03:32.198206: Hello!
```

**[ProducerConsumerSafe.java]** Write a program implementing two threads interacting via a producer-consumer communication scheme. The main thread, creates both producer and consumer, waits 100 ms, and gracefully terminates them (see Thread.interrupt() method). More specifically, the producer pushes integer objects on a shared queue, while the consumer fetches them. The consumer, before terminating, prints the total number of integers fetched from the queue (as represented below). **Both producer and consumer must be manually synchronized** on the shared queue before using it.

```text
Total number of consumed elements: 90065
```

**[ProducerConsumerUnsafe.java]** Write a program implementing two threads interacting via a producer-consumer communication scheme. The main thread, creates both producer and consumer, waits 100 ms, and gracefully terminates them (see Thread.interrupt() method). More specifically, the producer pushes integer objects on a shared queue, while the consumer fetches them. The consumer, before terminating, prints the total number of integers fetched from the queue (as represented below). **Both producer and consumer should not be synchronized on the shared queue but, instead, have to use a thread-safe collection**.

```text
Total number of consumed elements: 90065
```

**[ParallelPrimes.java]** The following class implements the `Callable<V>` interface which is frequently used to collect the return values of methods running on separate threads. Using the code provided, write a program searching prime numbers on a fixed number of parallel threads (see java.util.concurrent.ExecutorService). Each thread, receiving a range to be searched e.g. [0, 1000], [1000, 2000]..., returns a List<Integer> containing the prime numbers found. The main thread starts the parallel child threads using ExecutorService.invokeAll() and receives a `List<Future<List<Integer>>>` for fetching the results (see `java.util.concurrent.Future<V>`). 

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
