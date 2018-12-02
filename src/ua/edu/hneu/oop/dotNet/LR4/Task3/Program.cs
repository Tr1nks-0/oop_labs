using System;

namespace Task3
{
    class Program
    {

        static void Main(string[] args)
        {
            while (true)
            {
                try
                {
                    Console.Write("Please, write statement: ");

                    var input = Console.ReadLine();

                    Console.WriteLine("Result is: {0}\n", Calculator.Calculate(input));
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                }
            }
        }
    }
}
