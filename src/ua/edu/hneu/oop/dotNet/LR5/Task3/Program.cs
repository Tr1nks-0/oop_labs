using System;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace Task3
{
    class Program
    {
        private static readonly object SyncRoot = new object();

        static void Main(string[] args)
        {
            bool isSynchronized = true; // change value to change behavior
            Parallel.For(0, 10, i =>
            {
                if (isSynchronized)
                {
                    lock (SyncRoot)
                    {
                        Print();
                    }
                }
                else
                    Print();
            });
            Console.ReadKey();
        }

        private static void Print()
        {
            Random random = new Random();
            foreach (int i in Enumerable.Range(1, 10))
            {
                Console.WriteLine(i);
                Thread.Sleep(random.Next(0, 2000));
            }
        }
    }
}
