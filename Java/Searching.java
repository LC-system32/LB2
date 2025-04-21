public class Searching extends Thread {
    private final int startIndex;
    private final int endIndex;
    private final Mass mass;
    private final MinCollector collector;

    public Searching(int startIndex, int endIndex, Mass mass, MinCollector collector) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.mass = mass;
        this.collector = collector;
    }

    @Override
    public void run() {
        int[] minElement = mass.GetMinElement(startIndex, endIndex);
        System.out.println("In thread " + this.getName() + " min element was found " + minElement[0] + " which index " + minElement[1]);

        collector.collect(minElement[0], minElement[1]);
        collector.incThreadCount();
    }
}
