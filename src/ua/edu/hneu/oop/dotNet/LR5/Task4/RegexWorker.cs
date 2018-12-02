using System;
using System.Text.RegularExpressions;
using System.Xml;
using System.Xml.Schema;
using System.Xml.Serialization;

namespace Task4
{
    [Serializable]
    public class RegexWorker : IXmlSerializable
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

        public bool Contains() => Regex.IsMatch(Text);

        public void PrintAllMatches()
        {
            foreach (Match match in Regex.Matches(Text))
                Console.WriteLine(match.Value);
        }

        public void RemoveAllMatches() => Text = Regex.Replace(Text, string.Empty);

        public override string ToString() => $"Regex: {Regex}\nText: {Text}";
        public XmlSchema GetSchema() => null;

        public void ReadXml(XmlReader reader)
        {
            reader.ReadStartElement("RegexWorker");
            reader.ReadStartElement("worker");
            reader.ReadStartElement("regex");
            Regex = new Regex(reader.ReadString());
            reader.ReadEndElement();
            reader.ReadStartElement("text");
            Text = reader.ReadString();
            reader.ReadEndElement();
            reader.ReadEndElement();
            reader.ReadEndElement();
        }

        public void WriteXml(XmlWriter writer)
        {
            writer.WriteStartElement("worker");
            writer.WriteStartElement("regex");
            writer.WriteCData(Regex?.ToString() ?? string.Empty);
            writer.WriteEndElement();
            writer.WriteStartElement("text");
            writer.WriteCData(Text);
            writer.WriteEndElement();
            writer.WriteEndElement();
        }
    }
}

