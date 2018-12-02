using System;
using System.Text.RegularExpressions;

namespace LR4
{
    class Program
    {
        private const string Regex = "(((^|\\W)[eyuioa]\\w*)|(\\w*[eyuioa]($|\\W)))";
        private const string Text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        private const string Invitation =
            "Demonstration of work with RegexWorker class.\nWill be used pattern = {0}\nAnd text ={1}";

        private const string PassedTextContainsPatternFormat = "Is passed text contains pattern matches - {0}";
        private const string AllPatternMatches = "All pattern matches:";
        private const string TextAfterRemovingAllMatchesFormat = "Text after removing all matches - {0}";

        public static void Main(string[] args)
        {
            RegexWorker regexWorker = new RegexWorker
            {
                Regex = new Regex(Regex),
                Text = Text
            };

            Console.WriteLine(Invitation, Regex, Text);

            Console.WriteLine(PassedTextContainsPatternFormat, regexWorker.Contains());

            Console.WriteLine(AllPatternMatches);
            regexWorker.PrintAllMatches();

            regexWorker.RemoveAllMatches();
            Console.WriteLine(TextAfterRemovingAllMatchesFormat, regexWorker.Text);

            Console.ReadKey();
        }
    }
}
