using System;
using System.IO;

namespace SyntaxAnalizer
{
    class Program
    {
        private const string ExitCommand = "exit";
        private const string Invitation = "Enter testing formula or type \"exit\" for exit";
        private const string AnswerFormat = "Entered formula is {0}";

        private static readonly SyntaxAnalizer Analizer = new SyntaxAnalizer();

        public static void Main(string[] args)
        {
            Analizer.Init();
            bool run = true;
            do
            {
                Console.WriteLine(Invitation);
                try
                {
                    string input = Console.ReadLine();
                    if (CheckIfExit(input))
                        break;

                    Console.WriteLine(AnswerFormat, Analizer.IsFormula(input) ? "VALID" : "INVALID");

                }
                catch (IOException e)
                {
                    Console.WriteLine("Something went wrong with obtaining value. Original error is: {0}", e.Message);
                    run = false;
                }
            } while (run);
        }

        private static bool CheckIfExit(string input)
        {
            return input.ToLower().Contains(ExitCommand);
        }
    }
}
