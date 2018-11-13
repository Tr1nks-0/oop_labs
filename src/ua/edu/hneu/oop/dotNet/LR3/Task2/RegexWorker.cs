using System;
using System.Text.RegularExpressions;

namespace Task2
{
    public class RegexWorker
    {
        private Regex _regex;
        private string _text;

        public Regex Regex
        {
            get => _regex;
            set => _regex = value ?? throw new ArgumentNullException();
        }

        public string Text
        {
            get => _text;
            set => _text = value ?? throw new ArgumentNullException();
        }

        public bool Contains()
        {
            return Regex.IsMatch(Text);
        }

        public void PrintAllMatches()
        {
            foreach (Match match in Regex.Matches(Text))
                Console.WriteLine(match.Value);
        }

        public void RemoveAllMatches()
        {
            Text = Regex.Replace(Text, string.Empty);
        }
    }
}
