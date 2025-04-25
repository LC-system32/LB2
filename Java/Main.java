public class Main {
    public static void main(String[] args) {
        int size = 100_000_000;
        int numThreads = 5;
        Mass mass = new Mass(size);

        int segmentSize = size / numThreads;

        MinCollector collector = new MinCollector(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentSize;
            int end = (i == numThreads - 1) ? size - 1 : (start + segmentSize - 1);

            Searching threads = new Searching(start, end, mass, collector);
            threads.start();
        }

        int[] result = collector.getResult();
        System.out.println("Min value found in mass: " + result[0] + " which index: " + result[1]);
    }
}
