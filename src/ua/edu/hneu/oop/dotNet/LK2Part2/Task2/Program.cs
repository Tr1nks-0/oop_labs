using System;
using System.IO;

namespace Task2
{
    class Program
    {
        private const string FileName = "./sentences.txt";
        private const string Invitation = "All words start and end with vowel:";


        public static void Main(string[] args)
        {
            try
            {
                string text = File.ReadAllText(FileName);
                Console.WriteLine(Invitation);
                foreach (string word in text.FindAllVowelStartEndWords())
                    Console.WriteLine(word);
            }
            catch (IOException e)
            {
                Console.WriteLine(e.Message);
                Console.WriteLine(e.StackTrace);
            }

            Console.ReadKey();
        }
    }
}