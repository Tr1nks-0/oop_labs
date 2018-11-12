using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace Task2
{
    public static class WordFinder
    {
        private static readonly Regex WordPattern = new Regex(@"\b[eyuioa]\w*[eyuioa]\b", RegexOptions.Compiled | RegexOptions.CultureInvariant);

        public static HashSet<string> FindAllVowelStartEndWords(this string input)
        {
            HashSet<string> words = new HashSet<string>();
            foreach (Match match in WordPattern.Matches(input))
                words.Add(match.Value);

            return words;
        }
    }
}