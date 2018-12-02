using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace Task2
{
    public class StudentFactory
    {
        private const string SurnameRegex = "\\w";
        private const string InitialsRegex = "\\w\\.\\w*";
        private const string ArrayDelimiters = "[;,:]";
        private const string IntArrayRegex = "\\d *(" + ArrayDelimiters + " *\\d)* *";

        private const string SurnameInvitation = "Enter student's surname:";
        private const string InitialsInvitation = "Enter student's initials:";
        private const string GroupInvitation = "Enter student's group number:";
        private const string MarksInvitation = "Enter student's marks in format \"4,5,3,2,4\":";
        private const string WrongInput = "Wrong input. Please try again.\n";

        public Student CreateStudent()
        {
            Console.WriteLine(SurnameInvitation);
            String surname = GetString(validateSurname);
            Console.WriteLine(InitialsInvitation);
            String initials = GetString(ValidateInitials);
            Console.WriteLine(GroupInvitation);
            int group = GetGroup(ValidateGroup);
            Console.WriteLine(MarksInvitation);

            int[] marks = GetMarks();
            return new Student(surname, initials, group, marks);
        }

        private String GetString(Predicate<String> validator)
        {
            do
            {
                String input = Console.ReadLine();

                if (validator(input))
                    return input.Trim();

                Console.WriteLine(WrongInput);
            } while (true);
        }

        private int GetGroup(Predicate<int> validator)
        {
            do
            {
                string s = Console.ReadLine();

                if (int.TryParse(s, out int input) && validator(input))
                    return input;

                Console.WriteLine(WrongInput);
            } while (true);
        }

        private int[] GetMarks()
        {
            do
            {
                String input = Console.ReadLine();
                if (ValidateMarksArrayString(input))
                {
                    int[] arr = input.Split(ArrayDelimiters.Substring(1,3)
                        .ToCharArray())
                        .Select(x => x.Trim())
                        .Select(int.Parse)
                        .ToArray();

                    if (arr.Length == Student.MarksSize)
                        return arr;
                }
                Console.WriteLine(WrongInput);
            } while (true);
        }

        private bool validateSurname(String surname) => !string.IsNullOrEmpty(surname) && Regex.IsMatch(surname, SurnameRegex);

        private bool ValidateInitials(String initials) => !string.IsNullOrEmpty(initials) && Regex.IsMatch(initials, InitialsRegex);

        private bool ValidateGroup(int group) => group > 0;

        private bool ValidateMarksArrayString(String input) => !string.IsNullOrEmpty(input) && Regex.IsMatch(input, IntArrayRegex);
    }
}