public class Searching extends Thread {
    private final int startIndex;
    private final int endIndex;
    private final Mass mass;

    private static volatile int globalMin = Integer.MAX_VALUE;
    private static volatile int globalMinIndex = 0;

    public static synchronized void updateGlobalMin(int localMin, int localMinIndex) {
        if (localMin < globalMin) {
            globalMin = localMin;
            globalMinIndex = localMinIndex;
        }
    }

    public static int getGlobalMin() {
        return globalMin;
    }

    public static int getGlobalMinIndex() {
        return globalMinIndex;
    }

    public Searching(int startIndex, int endIndex, Mass mass) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.mass = mass;
    }

    @Override
    public void run() {
        int[] minElement = mass.GetMinElement(startIndex, endIndex);
        System.out.println("In thread " + this.getName() +" min element was found " + minElement[0] + " which index " + minElement[1]);

        updateGlobalMin(minElement[0], minElement[1]);
    }
}
