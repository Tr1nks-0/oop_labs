using System;

using FunctionValueCaculator.Engine;

namespace FunctionValueCaculator
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                Console.Write("Value of argument: ");
                string userInput = Console.ReadLine() ?? throw new ApplicationException("Inpuc can't be null.");

                double argument = Double.Parse(userInput);

                ComplexFunction function = new ComplexFunction();
                double result = function.Calculate(argument);

                Console.WriteLine($"Result: {result}");
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }

            Console.ReadKey();
        }
    }
}
