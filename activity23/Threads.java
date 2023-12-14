public class Threads {

    private static long balance = 0;

    private synchronized static void deposit(long amount) {
        // With "synchronized," we lock a mutex at the beginning of the method...
        if (amount > 0)
            balance += amount;
        // ...and unlock it at the end.
    }

    private static void makeDeposits(int count, int amount) {
        for (int i = 0; i < count; ++i)
            deposit(amount);
    }

    public static void main(String[] args) {
        final int amount = 250;
        final int NUM_THREADS = 1;
        final int NUM_DEPOSITS = 100_000_000;

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; ++i) {
            threads[i] = new Thread(() -> {
                makeDeposits(NUM_DEPOSITS / NUM_THREADS, amount);
            });
        }

        for (int i = 0; i < NUM_THREADS; ++i)
            threads[i].start();

        try {
            for (int i = 0; i < NUM_THREADS; ++i)
                threads[i].join();
        } catch (InterruptedException ex) {}

        System.out.printf("Final balance: $%,d\n", balance);
    }
}
