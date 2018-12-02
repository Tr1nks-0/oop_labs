using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization.Formatters.Soap;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Task4
{
    class Program
    {
        static void Main(string[] args)
        {
            RegexWorker[] workers =
            {
                new RegexWorker
                {
                    Regex = new Regex("\\d"),
                    Text = "Text"
                },
                new RegexWorker
                {
                    Regex = new Regex("\\w"),
                    Text = "AnotherText"
                }
            };

            //Serialization

            using (Stream stream = new FileStream("xmloutput.txt", FileMode.OpenOrCreate, FileAccess.Write))
            {
                XmlSerializer format = new XmlSerializer(workers.GetType());
                format.Serialize(stream, workers);
            }

            using (Stream stream = new FileStream("soapoutput.txt", FileMode.OpenOrCreate, FileAccess.Write))
            {
                SoapFormatter format = new SoapFormatter();
                format.Serialize(stream, workers);
            }

            using (Stream stream = new FileStream("binaryoutput.txt", FileMode.OpenOrCreate, FileAccess.Write))
            {
                BinaryFormatter format = new BinaryFormatter();
                format.Serialize(stream, workers);
            }

            //Deserialization

            Console.WriteLine("XML");
            using (Stream stream = new FileStream("xmloutput.txt", FileMode.Open, FileAccess.Read))
            {
                XmlSerializer format = new XmlSerializer(workers.GetType());
                foreach (RegexWorker worker in (RegexWorker[])format.Deserialize(stream))
                    Console.WriteLine(worker);
            }

            Console.WriteLine("SOAP");
            using (Stream stream = new FileStream("soapoutput.txt", FileMode.Open, FileAccess.Read))
            {
                SoapFormatter format = new SoapFormatter();
                foreach (RegexWorker worker in (RegexWorker[]) format.Deserialize(stream))
                    Console.WriteLine(worker);
            }

            Console.WriteLine("BINARY");
            using (Stream stream = new FileStream("binaryoutput.txt", FileMode.Open, FileAccess.Read))
            {
                BinaryFormatter format = new BinaryFormatter();
                foreach (RegexWorker worker in (RegexWorker[]) format.Deserialize(stream))
                    Console.WriteLine(worker);
            }

            Console.ReadKey();
        }
    }
}