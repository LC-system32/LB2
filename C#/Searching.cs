using System;

namespace LB1
{
    public class Searching
    {
        private readonly int idThread;
        private int start;
        private int end;
        private Mass mass;
        private MinCollector collector;

        public Searching(int id, int start, int end, Mass mass, MinCollector collector)
        {
            idThread = id;
            this.start = start;
            this.end = end;
            this.mass = mass;
            this.collector = collector;
        }

        public void Run()
        {
            int[] minElement = mass.GetMinElement(start, end);

            Console.WriteLine($"In thread {idThread} min element was found {minElement[0]} which index {minElement[1]}");

            collector.Collect(minElement[0], minElement[1]);
        }
    }
}
