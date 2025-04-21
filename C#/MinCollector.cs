using System.Threading;

namespace LB1
{
    public class MinCollector
    {
        private int globalMin = int.MaxValue;
        private int globalMinIndex = -1;
        private int finishedThreads = 0;
        private readonly int totalThreads;
        private readonly object lockObj = new object();

        public MinCollector(int threadCount)
        {
            totalThreads = threadCount;
        }

        public void Collect(int localMin, int localMinIndex)
        {
            lock (lockObj)
            {
                if (localMin < globalMin)
                {
                    globalMin = localMin;
                    globalMinIndex = localMinIndex;
                }

                finishedThreads++;

                if (finishedThreads == totalThreads)
                {
                    Monitor.Pulse(lockObj); // будимо потік, який чекає
                }
            }
        }

        public (int, int) GetResult()
        {
            lock (lockObj)
            {
                while (finishedThreads < totalThreads)
                {
                    Monitor.Wait(lockObj); // чекаємо завершення всіх потоків
                }

                return (globalMin, globalMinIndex);
            }
        }
    }
}
