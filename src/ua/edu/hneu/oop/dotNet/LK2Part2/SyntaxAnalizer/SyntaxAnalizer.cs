using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace SyntaxAnalizer
{
    public class SyntaxAnalizer
    {
        private static string SIGN_GROUP_NAME = "sign";
        private static string CONTENT_GROUP_NAME = "content";
        private static string NAME_REGEX = "[xyz]";

        private static readonly Regex SumCombinationPattern =
            new Regex("((\\(.*\\))|(\\{.*})|(\\[.*])|[xyz]) *(?<" + SIGN_GROUP_NAME +
                      ">[+-]) *((\\(.*\\))|(\\{.*})|(\\[.*])|[xyz])");

        private static readonly Regex[] BracketedRegexes =
        {
            new Regex("^(\\()(?<" + CONTENT_GROUP_NAME + ">.*)(\\))$", RegexOptions.Compiled),
            new Regex("^(\\[)(?<" + CONTENT_GROUP_NAME + ">.*)(])$", RegexOptions.Compiled),
            new Regex("^(\\{)(?<" + CONTENT_GROUP_NAME + ">.*)(})$", RegexOptions.Compiled)
        };

        private List<string> _errors;

        public void Init()
        {
            _errors = new List<string>();
        }

        public List<string> GetErrors() => _errors;

        public bool IsName(string input)
        {
            string trimmed = input.Trim();

            return !string.IsNullOrEmpty(trimmed) && Regex.IsMatch(trimmed, NAME_REGEX);
        }

        public bool IsTerm(string input)
        {
            return IsName(input) ||
                   isBracketed(input, IsFormula);
        }

        public bool IsFormula(string input)
        {
            return isSumCombination(input, IsTerm, IsFormula) ||
                   IsTerm(input);
        }

        private bool isSumCombination(string input, Predicate<string> firstPredicate, Predicate<string> secondPredicate)
        {
            string trimmed = input.Trim();
            Match match = SumCombinationPattern.Match(trimmed);
            if (match.Success)
            {
                int index = trimmed.IndexOf(match.Groups[SIGN_GROUP_NAME].Value, StringComparison.Ordinal);
                return firstPredicate(trimmed.Substring(0, index)) &&
                       secondPredicate(trimmed.Substring(index + 1));
            }
            return false;
        }


        private bool isBracketed(string input, Predicate<string> bracedPredicate)
        {
            string trimmed = input.Trim();
            foreach (Regex pattern in BracketedRegexes)
            {
                Match match = pattern.Match(trimmed);
                if (match.Success)
                    return bracedPredicate(match.Groups[CONTENT_GROUP_NAME].Value);
            }

            return false;
        }
    }
}