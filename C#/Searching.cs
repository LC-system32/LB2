using System;
using System.Net.Mail;
using System.Reflection.Emit;
using System.Threading;

namespace LB1
{
    public class Searching
    {
        private readonly int idThread;

        private int start;
        private int end;
        private Mass mass;

        public static int GlobalMin { get; private set; }
        public static int GlobalMinIndex { get; private set; }

        private readonly object lockObj = new object();

        public Searching(int id, int start, int end, Mass mass)
        {
            idThread = id;

            this.start = start;
            this.end = end;
            this.mass = mass;
        }

        public void updateGlobalMin(int localMin, int localMinIndex)
        {
            lock(lockObj)
            {
                if (localMin < GlobalMin)
                {
                    GlobalMin = localMin;
                    GlobalMinIndex = localMinIndex;
                }
            }
        }

        public void Run()
        {
            int[] minElement = mass.GetMinElement(start, end);

            Console.WriteLine($"In thread {idThread} min element was found {minElement[0]} which index {minElement[1]}\n");

            updateGlobalMin(minElement[0], minElement[1]);
        }
    }
}