using System;
using System.Collections.Generic;
using System.IO;

namespace LR5
{
    class Program
    {
        public const string PathToFile = "./source.txt";

        static void Main(string[] args)
        {
            string content = File.ReadAllText(PathToFile);
            Console.WriteLine(ProcessString(content));
            Console.ReadKey();
        }

        private static string ProcessString(string text)
        {
            Stack<char> chars = new Stack<char>();
            int squareCount = 0;
            for (var i = text.Length - 1; i >= 0; i--)
            {
                char c = text[i];
                if (c == '#')
                    squareCount++;
                else
                {
                    if (squareCount == 0)
                        chars.Push(c);
                    else
                        squareCount--;
                }
            }

            return new string(chars.ToArray());
        }
    }
}
