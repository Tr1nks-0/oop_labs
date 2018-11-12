using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace Task1
{
    public static class SentenceFinder
    {
        private static readonly Regex SentenceRegex = new Regex("(?<= )*([\\w, :-]*[.!?])(?= )*", RegexOptions.Compiled);

        public static List<string> SplitOnSentences(string input)
        {
            List<string> sentences = new List<string>();

            foreach (Match match in SentenceRegex.Matches(input))
                sentences.Add(match.Value);

            return sentences;
        }
    }
}
