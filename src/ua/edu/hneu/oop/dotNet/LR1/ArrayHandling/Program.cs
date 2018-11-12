using System;
using System.Linq;

namespace ArrayHandling
{
    class Program
    {
        private const string AnswerFormat =
            "Minimal value = {0}\nSum after first zero ={1}\nOriginal array:{2}\nReordered array:{3}\n";

        static void Main(string[] args)
        {
            try
            {
                Console.WriteLine("Enter size of array: ");
                string userInput = Console.ReadLine();

                int sizeOfArray = int.Parse(userInput ?? throw new ApplicationException("Inpuc can't be null."));

                int[] array = InitializeArray(sizeOfArray);

                int min = GetMinAbsElement(array);;
                int sum = GetSumAfterZero(array);
                int[] reordered = ReorderArray(array);

                Console.WriteLine(
                    AnswerFormat,
                    min,
                    sum,
                    string.Join(", ", array),
                    string.Join(", ", reordered));
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Console.WriteLine(e);
            }

            Console.ReadKey();
        }

        private static int[] InitializeArray(int size)
        {
            int[] array = new int[size];
            Random random = new Random();
            for (var i = 0; i < array.Length; i++)
                array[i] = random.Next(-100, 100);

            return array;
        }

        private static int GetMinAbsElement(int[] array)
        {
            return array.Min(Math.Abs);
        }

        private static int GetSumAfterZero(int[] array)
        {
            return array.SkipWhile(x => x != 0).Sum();
        }

        private static int[] ReorderArray(int[] array)
        {
            int[] result = new int[array.Length];

            int index = 0;

            for (int i = 0; i < array.Length; i += 2, index++)
                result[index] = array[i];

            for (int i = 1; i < array.Length; i += 2, index++)
                result[index] = array[i];

            return result;
        }
    }
}