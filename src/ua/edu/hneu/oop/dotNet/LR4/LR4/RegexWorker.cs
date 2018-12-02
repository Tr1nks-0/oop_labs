using System;
using System.Text.RegularExpressions;

namespace LR4
{
    public class RegexWorker
    {
        private Regex _regex;
        private string _text;

        public object this[int index]
        {
            get
            {
                switch (index)
                {
                    case 0:
                        return Regex;
                    case 1:
                        return Text;
                    default:
                        throw new ArgumentOutOfRangeException("Invalid index provided.");
                }
            }
        }

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

        public static RegexWorker operator -(RegexWorker obj)
        {
            obj.Regex.Replace(obj.Text, string.Empty);

            return obj;
        }

        public static bool operator false(RegexWorker obj) => string.IsNullOrEmpty(obj.Text);

        public static bool operator true(RegexWorker obj) => !string.IsNullOrEmpty(obj.Text);

        public static RegexWorker operator +(RegexWorker worker, string str)
        {
            worker.Text += str;
            return worker;
        }

        public static explicit operator string(RegexWorker regex) => regex.Text;

        public static explicit operator RegexWorker(string text) => new RegexWorker {Text = text};
    }
}
