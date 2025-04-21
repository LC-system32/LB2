using System;

namespace LB1
{
    public class Mass
    {
        private int[] data;
        public int Length => data.Length;
        public Mass(int size)
        {
            data = new int[size];
                Random random = new Random();
            for (int i = 0; i < size ; i++)
            {
                data[i] = random.Next(0, 100);
            }

            data[5] = -1;
        }

        public int[] GetMinElement(int start, int end)
        {
            int minVal = int.MaxValue;
            int minIndex = start;

            for (int i = start; i <= end; i++)
            {
                if (data[i] < minVal)
                {
                    minVal = data[i];
                    minIndex = i;
                }
            }

            return new int[] { minVal, minIndex };
        }
    }

}
