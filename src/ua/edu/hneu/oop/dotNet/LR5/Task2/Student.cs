using System;
using System.Linq;

namespace Task2
{
    [Serializable]
    public class Student
    {
        public static long SerialVersionUid { get; } = 1L;
        public static int MarksSize = 5;

        public static String Header = $"{"№",-3} | {"Surname",-16} |{"Initials",-8}| {"Group",-5} | {"Marks",17} |{"average",-7}\n";

        public static String RowFormat = "{0,-3} | {1,-16} | {2,-6} | {3,-5} | {4} | {5} | {6} | {7} | {8} | {9}\n";

        public Student()
        {
            Marks = new int[MarksSize];
        }

        public Student(String surname, String initials, int group) : this()
        {
            Surname = surname;
            Initials = initials;
            Group = group;
        }

        public Student(String surname, String initials, int group, int[] marks)
            : this(surname, initials, group)
        {
            Marks = marks;
        }

        public string Surname { get; set; }

        public string Initials { get; set; }

        public int Group { get; set; }

        public int[] Marks { get; set; }

        public int GetMark(int index)
        {
            ThrowIfMarkIndexInvalid(index);
            return Marks[index];
        }

        public void SetMark(int index, int mark)
        {
            ThrowIfMarkIndexInvalid(index);
            Marks[index] = mark;
        }

        public string ToTableString(int rowIndex)
        {
            return string.Format(RowFormat, rowIndex, Surname, Initials, Group, Marks[0], Marks[1], Marks[2],
                Marks[3], Marks[4], GetAverageMark());
        }

        public double GetAverageMark() => Marks.Average();

        private void ThrowIfMarkIndexInvalid(int index)
        {
            if (index < 0 || index > MarksSize - 1)
                throw new ArgumentOutOfRangeException("Index should be in range [ 0 ; " + (MarksSize - 1) + " ]");
        }
    }
}