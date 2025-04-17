public class Main {
    public static void main(String[] args) {
        int size = 100_000_0000;
        int numThreads = 5;
        Mass mass = new Mass(size);

        int segmentSize = size / numThreads;
        Searching[] threads = new Searching[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentSize;
            int end = (i == numThreads - 1) ? size - 1 : (start + segmentSize - 1);
            threads[i] = new Searching(start, end, mass);
            threads[i].start();
        }

        for (Searching thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Min value found in mass: " + Searching.getGlobalMin() + " which index: " + Searching.getGlobalMinIndex());
    }
}
