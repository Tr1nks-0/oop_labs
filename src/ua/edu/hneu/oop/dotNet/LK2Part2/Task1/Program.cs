using System;
using System.Collections.Generic;
using System.IO;

using static Task1.SentenceFinder;

namespace Task1
{
    class Program
    {
        private const string FileName = "./sentences.txt";

        public static void Main(string[] args)
        {
            try
            {
                Console.WriteLine("For highlight next sentence press any button.\nAfter last sentence total sentences count will be shown");

                string text = File.ReadAllText(FileName);
                List<string> sentences = SplitOnSentences(text);
                Console.WriteLine("Total sentences count = {0}", sentences.Count);

                int sentenceIndex = 0;
                while (sentenceIndex < sentences.Count)
                {
                    int wordIndex = 0;
                    string[] words = sentences[sentenceIndex].Split(' ');
                    while (wordIndex < words.Length)
                    {
                        Console.ReadKey();
                        Console.Clear();
                        PrintSentences(sentences, sentenceIndex, wordIndex++);
                    }

                    sentenceIndex++;
                }
            }
            catch (IOException e)
            {
                Console.WriteLine(e.Message);
            }

            Console.ReadKey();
        }

        private static void PrintSentences(List<string> sentences, int sentenceIndex, int wordIndex)
        {
            for (int i = 0; i < sentenceIndex; i++)
                Console.Write(sentences[i]);

            PrintSelectedSentence(sentences[sentenceIndex], wordIndex);

            for (int i = sentenceIndex + 1; i < sentences.Count; i++)
                Console.Write(sentences[i]);
        }

        private static void PrintSelectedSentence(string sentence, int wordIndex)
        {
            string[] words = sentence.Split(' ');
            Console.ForegroundColor = ConsoleColor.Yellow;
            for (var i = 0; i < wordIndex; i++)
                Console.Write(words[i] + " ");

            Console.ForegroundColor = ConsoleColor.Blue;
            Console.Write(words[wordIndex] + " ");

            Console.ForegroundColor = ConsoleColor.Yellow;
            for (var i = wordIndex + 1; i < words.Length; i++)
                Console.Write(words[i] + " ");

            Console.ResetColor();
        }
    }
}