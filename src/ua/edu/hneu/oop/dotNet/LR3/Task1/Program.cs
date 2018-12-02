using System;
using System.Linq;

namespace Task1
{
    class Program
    {
        static void Main(string[] args)
        {
            Marsh[] marshes = new Marsh[2];

            for (var i = 0; i < marshes.Length; i++)
                marshes[i] = GetMarsh();

            Console.Clear();
            Console.WriteLine("Obtained marshes:");
            foreach (Marsh marsh in marshes.OrderBy(x => x.Number))
                Console.WriteLine(marsh);

            bool repeat;
            do
            {
                try
                {
                    repeat = false;
                    Console.WriteLine("Please, write mursh number:");
                    string input = Console.ReadLine();
                    int marshNumber = Int32.Parse(input ?? throw new InvalidOperationException("Value can not be null."));
                    Marsh marsh = marshes.FirstOrDefault(x => x.Number == marshNumber);
                    if (marshes.Any(x => x.Number == marshNumber))
                        Console.WriteLine(marsh);
                    else
                        Console.WriteLine($"Marsh with number {marshNumber} was not found.");
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);

                    repeat = true;
                }
            } while (repeat);

            Console.ReadKey();
        }

        private static Marsh GetMarsh()
        {
            bool repeat;
            do
            {
                try
                {
                    repeat = false;
                    Console.WriteLine("Please, write start point of a marsh:");
                    string startPoint = Console.ReadLine();

                    Console.WriteLine("Please, write end point of a marsh:");
                    string endPoint = Console.ReadLine();

                    Console.WriteLine("Please, write marsh number: ");
                    string marshNumber = Console.ReadLine();

                    return new Marsh
                    {
                        EndPoint = endPoint,
                        StartPoint = startPoint,
                        Number = Int32.Parse(marshNumber ?? throw new InvalidOperationException("Input can't be null"))
                    };
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);

                    repeat = true;
                }

            } while (repeat);

            throw new ApplicationException();
        }
    }
}
