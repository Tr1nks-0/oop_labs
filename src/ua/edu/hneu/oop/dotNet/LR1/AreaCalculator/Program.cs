using System;

namespace AreaCalculator
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int rangeSize = 1000;
            double lowBound = 1.0;
            double highBound = 2.0;

            double result = CalculateArea(lowBound, highBound, rangeSize);
            Console.WriteLine($"Result = {result}");

            Console.ReadKey();
        }

        static double GetValueOfFunction(double argument) => argument * argument - 0.5 * argument;

        public static double CalculateArea(double lowBound, double highBound, int rangeSize)
        {
            double sum = 0.0;
            double delta = (highBound - lowBound) / rangeSize;
            for (int i = 0; i < rangeSize; i++)
                sum += 0.5 * delta * (GetValueOfFunction(lowBound + i * delta) + GetValueOfFunction(lowBound + (i + 1) * delta));

            return sum;
        }
    }
}
