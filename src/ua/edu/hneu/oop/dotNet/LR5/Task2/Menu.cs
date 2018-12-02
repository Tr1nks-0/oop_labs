using System;
using System.Collections.Generic;
using System.Linq;

namespace Task2
{
    public class Menu
    {
        private const String Invitation = "Enter a command:\n>>>";
        private const String HelpDescription = "Print this help.";
        private const String DescriptionFormat = "{0,-10}  ---  {1}";
        private const String PatternFormat = "{0}";
        private const String CommandNotFound = "Entered command not found. For help enter help.";

        private static readonly String[] HelpCommandKeys = {"h", "help"};

        private readonly List<String> _menuDescriptions = new List<string>();
        private readonly Dictionary<String, Action> _menuItems = new Dictionary<string, Action>();

        public Menu()
        {
            AddMenuRow(HelpDescription, PrintHelp, HelpCommandKeys);
        }

        public void AddMenuRow(String description, Action action, params string[] keys)
        {
            AppendDescription(description, keys);
            _menuItems.Add(ToPattern(keys), action);
        }

        public void Process()
        {
            Console.WriteLine(Invitation);
            ProcessInput(Console.ReadLine());
        }

        public void PrintHelp()
        {
            foreach (string description in _menuDescriptions)
                Console.WriteLine(description);
            Console.WriteLine("%n");
        }

        private void ProcessInput(String command)
        {
            string key = _menuItems.Keys.FirstOrDefault(x => x.Split('|').Contains(command));
            if (key == null)
                DefaultCommand();
            else
                _menuItems[key]();
        }

        private void DefaultCommand() => Console.WriteLine(CommandNotFound);

        private void AppendDescription(String description, params string[] keys)
        {
            _menuDescriptions.Add(String.Format(DescriptionFormat, KeysJoining(keys), description));
        }

        private String ToPattern(params string[] keys) => String.Format(PatternFormat, KeysJoining(keys));

        private String KeysJoining(params string[] keys) => String.Join("|", keys);
    }
}