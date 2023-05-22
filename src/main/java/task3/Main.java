package task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int LIMIT = 20;
    private static final int THREADS = 2;
    private static final File RESULT_FILE = new File("Result.txt");

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        ConcurrentMap<Integer, Integer> primes = new ConcurrentSkipListMap<>();
        AtomicInteger nextThread = new AtomicInteger(0);

        for (int i = 2; i <= LIMIT; i++) {
            final int number = i;
            executor.submit(() -> {
                if (isPrime(number)) {
                    primes.put(number, nextThread.getAndIncrement());
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Что-то пошло не так");
        }

        BufferedWriter[] threadWriters = new BufferedWriter[THREADS];
        BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_FILE));

        for (int i = 0; i < THREADS; i++) {
            File threadFile = new File("Thread" + (i + 1) + ".txt");
            threadWriters[i] = new BufferedWriter(new FileWriter(threadFile));
        }

        for (int number : primes.keySet()) {
            int threadIndex = primes.get(number) % THREADS;
            String s = number + " ";
            threadWriters[threadIndex].write(s);
            writer.write(s);
        }

        for (BufferedWriter threadWriter : threadWriters) {
            threadWriter.close();
        }
        writer.close();
    }

    private static boolean isPrime(int number) {
        if (number <= 1 || (number > 2 && number % 2 == 0)) {
            return false;
        }

        int limit = (int) Math.sqrt(number);
        for (int i = 3; i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
