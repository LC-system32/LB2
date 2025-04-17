import java.util.Random;

public class Mass {
    private final int[] mass;

    public Mass(int sizeOfMass)
    {
        Random rand = new Random();
        mass = new int[sizeOfMass];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = rand.nextInt(0,100);
        }

        mass[5] = -1;
    }

    synchronized int[] GetMinElement(int start, int end)
    {
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = start; i <= end; i++) {
            if (mass[i] < minValue) {
                minValue = mass[i];
                minIndex = i;
            }
        }

        return new int[]{minValue, minIndex};
    }
}
