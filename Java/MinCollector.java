public class MinCollector {
    private final int threadNum;
    private int threadCount = 0;
    private int globalMin = Integer.MAX_VALUE;
    private int globalMinIndex = 0;

    public MinCollector(int threadNum) {
        this.threadNum = threadNum;
    }

    public synchronized void collect(int min, int index) {
        if (min < globalMin) {
            globalMin = min;
            globalMinIndex = index;
        }
    }

    public synchronized void incThreadCount() {
        threadCount++;
        if (threadCount == threadNum) {
            notify();
        }
    }

    public synchronized int[] getResult() {
        while (threadCount < threadNum) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new int[]{globalMin, globalMinIndex};
    }
}
