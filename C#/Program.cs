using System;
using System.Threading;

namespace LB1
{
    class Program
    {
        static void Main(string[] args)
        {
            int size = 100_000_000;
            int threadCount = 5;

            Mass mass = new Mass(size);
            MinCollector collector = new MinCollector(threadCount);

            int segmentSize = size / threadCount;
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++)
            {
                int start = i * segmentSize;
                int end = (i == threadCount - 1) ? size - 1 : (start + segmentSize - 1);

                var searching = new Searching(i, start, end, mass, collector);
                threads[i] = new Thread(searching.Run);
                threads[i].Start();
            }

            var result = collector.GetResult();
            Console.WriteLine($"Global min: {result.Item1} at index {result.Item2}");
        }
    }
}
