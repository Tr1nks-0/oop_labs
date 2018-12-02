using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization.Formatters.Soap;
using System.Text.RegularExpressions;
using System.Xml.Serialization;

namespace Task2
{
    class Program
    {
        private const string IoExceptionFormat =
            "Something went wrong with obtaining value. Original error is:\"{0}\".\n\n";

        private const string YesRegex = "y|yes";

        private static readonly Comparison<Student> SurnameComparator = (student, student1) => String.CompareOrdinal(student.Surname, student1.Surname);
        private static readonly Comparison<Student> GroupComparator = (student, student1) =>  Math.Sign(student.Group.CompareTo(student1.Group));

        private static readonly Comparison<Student> AverageMarkComparator = (student, student1) => (int)(student.GetAverageMark() - student1.GetAverageMark());

        private static readonly Comparison<Student>[] Comparators =
        {
            SurnameComparator, GroupComparator, AverageMarkComparator
        };

        private static readonly Func<Student, String, bool> SurnameSearch = (student, phrase) => student.Surname.Contains(phrase);

        private static readonly Func<Student, String, bool> InitialsSearch = (student, phrase)=>student.Initials.Contains(phrase);

        private static readonly Func<Student, String, bool> GroupSearch = (student, phrase)=>student.Group.ToString().Contains(phrase);

        private static readonly Func<Student, String, bool> AverageMarkSearch = (student, phrase)=>student.GetAverageMark().ToString().Contains(phrase);

        private static readonly Func<Student, String, bool>[] Searches =
        {
            SurnameSearch, InitialsSearch, GroupSearch, AverageMarkSearch
        };

        private static List<Student> _students = new List<Student>();
        private static readonly Menu MainMenu = new Menu();
        private static readonly StudentFactory StudentFactory = new StudentFactory();

        private static bool _run = true;

        public static void Main(String[] args)
        {
            Initialize();
            MainMenu.PrintHelp();
            do
            {
                try
                {
                    MainMenu.Process();
                }
                catch (ArgumentException e)
                {
                    Console.WriteLine(e.Message);
                }
                catch (IOException e)
                {
                    Console.WriteLine(IoExceptionFormat, e.Message);
                }
            } while (_run);
        }

        private static void List()
        {
            Console.WriteLine("\n");
            if (_students.Count == 0)
                Console.WriteLine("Students list is empty - nothing to show.");
            else
                PrintStudentsList(_students);

            Console.WriteLine("\n");
        }

        private static void AddNewStudent()
        {
            Student student = StudentFactory.CreateStudent();
            _students.Add(student);
            _students.Sort(AverageMarkComparator);
            Console.WriteLine("Student successfully was added");
        }

        private static void ReadStudentsFromFile()
        {
            Console.WriteLine("Enter path to file");
            String path = Console.ReadLine();

            if (File.Exists(path))
            {
                using (Stream fStream = new FileStream(path, FileMode.Open, FileAccess.Read, FileShare.None))
                {
                    XmlSerializer format = new XmlSerializer(typeof(List<Student>));

                    _students = (List<Student>)format.Deserialize(fStream);
                }
                Console.WriteLine("Students successfully read from file");
            }
            else
            {
                throw new ArgumentException("File \"" + path + "\" is not exists or can't be read.");
            }
        }

        private static void WriteStudentsToFile()
        {
            Console.WriteLine("Enter path to file");
            String path = Console.ReadLine();
            using (Stream fStream = new FileStream(path, FileMode.OpenOrCreate, FileAccess.Write, FileShare.None))
            {
                XmlSerializer format = new XmlSerializer(typeof(List<Student>));
                format.Serialize(fStream, _students);
            }
        }

        private static void Sort()
        {
            Console.WriteLine("To sort list enter number of row to sort\n\t1 - surname\n\t2 - group\n\t3 - average mark\n");
            String input = Console.ReadLine();
            try
            {
                int index = int.Parse(input);
                if (index < 1 || index > 3)
                    throw new ArgumentOutOfRangeException("Entered number is out of range");
                _students.Sort(Comparators[index - 1]);
                Console.WriteLine("Successfully sorted");
            }
            catch (FormatException)
            {
                throw new ArgumentException("Entered value can not be interpreted as number");
            }
        }

        private static void Search()
        {
            Console.WriteLine("Enter number of row where search\n\t1 - surname\n\t2 - initials\n\t3 - group\n\t4 - average mark\n");
            String input = Console.ReadLine();
            try
            {
                int index = int.Parse(input);
                if (index < 1 || index > 3)
                    throw new ArgumentOutOfRangeException("Entered number is out of range");
                Console.WriteLine("Enter phrase to search");
                String phrase = Console.ReadLine();
                List<Student> result = _students.Where(s => Searches[index - 1](s, phrase)).ToList();
                if (result.Count == 0)
                {
                    Console.WriteLine("Nothing was found");
                }
                else
                {
                    PrintStudentsList(result);
                    Console.WriteLine();
                }
            }
            catch (FormatException)
            {
                throw new ArgumentException("Entered value can not be interpreted as number");
            }
        }

        private static void Print()
        {
            List<Student> filtered = _students.Where(student => {
                foreach (int mark in student.Marks)
                {
                    if (mark > 3)
                    {
                        return true;
                    }
                }
                return false;
            }).ToList();
            if (filtered.Count == 0)
            {
                Console.WriteLine("There are not any student with mark greater than 3");
            }
            else
            {
                foreach (Student student in filtered)
                    Console.WriteLine("{0,-16} | {1,-6} | {2,-5}\n", student.Surname, student.Initials, student.Group);
            }
        }

        private static void Delete()
        {
            Console.WriteLine("Enter number of student to be deleted");
            String input = Console.ReadLine();
            try
            {
                int index = int.Parse(input);
                if (index < 0 || index > _students.Count)
                    throw new ArgumentOutOfRangeException("Entered number is out of range");
                _students.RemoveAt(index - 1);
                Console.WriteLine("Student successfully was deleted");
            }
            catch (FormatException)
            {
                throw new ArgumentException("Entered value can not be interpreted as number");
            }
        }

        private static void Clear()
        {
            Console.WriteLine("This operation lead to erasing all students in current list. Are You sure?(y/n):");
            String input = Console.ReadLine();
            if (Regex.IsMatch(input, YesRegex))
            {
                _students.Clear();
                Console.WriteLine("Students list was successfully cleared");
            }
        }

        private static void Exit()
        {
            _run = false;
            Console.WriteLine("Exiting ...");
        }

        private static void Initialize()
        {
            MainMenu.AddMenuRow("Add new student to current list.", AddNewStudent, "a", "add");
            MainMenu.AddMenuRow("Open existing file.", ReadStudentsFromFile, "o", "open");
            MainMenu.AddMenuRow("Print all students .", List, "l", "list");
            MainMenu.AddMenuRow("Sort current list.", Sort, "s", "sort");
            MainMenu.AddMenuRow("Find by current list.", Search, "f", "find");
            MainMenu.AddMenuRow("Print all students with marks greater than 3.", Print, "p", "print");
            MainMenu.AddMenuRow("Write current list to file.", WriteStudentsToFile, "w", "write");
            MainMenu.AddMenuRow("Delete student from current list.", Delete, "d", "delete");
            MainMenu.AddMenuRow("Clear current list.", Clear, "c", "clear");
            MainMenu.AddMenuRow("Exit.", Exit, "e", "exit");
        }

        private static void PrintStudentsList(List<Student> filtered)
        {
            Console.Write(Student.Header);
            for (int i = 0; i < filtered.Count; i++)
                Console.WriteLine(filtered[i].ToTableString(i + 1));
        }
    }
}