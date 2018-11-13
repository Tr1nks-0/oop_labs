using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;


namespace Task3
{
    /// <summary>
    /// Please note. At 55 and 56 line you could see solution for Task3 And Task 4. Uncomment one of them to see result.
    /// </summary>
    class Program
    {
        private static string EXIT_COMMAND = "exit";

        private static string NOT_VALID_NUMBER_EXCEPTION = "You enter not valid number. Please correct Your input and try again.";

        private static string IO_EXCEPTION_FORMAT =
            "Something went wrong with obtaining value. Original error is:\"{0}\".\n\n";

        private static string ILLEGAL_ARGUMENT_EXCEPTION_FORMAT = "{0} Please correct Your input and try again.";
        private static string EMPTY_PARAMETERS_MESSAGE = "Expected one parameter - double number. Obtained 0.";
        private static string WRONG_FIRST_PARAMETER_FORMAT = "Wrong first parameter. Expected double, got: {0}";

        private static string INVITATION = "Enter x value or \"exit\" for exit";

        private static readonly string DelimiterLine = new string('-', 20);

        private static double a = 12.2;
        private static double b = 8.75;
        private static double c = -1.44;
        private static string PARAM_FORMAT = "{0}={1}";
        private static string HEADER_FORMAT = "For function {0}={1}\nwith parameters:\n{2}\nwhen x={3}\n";
        private static string VALUE_FORMAT = "value = {0}";
        private static string VALUE_EXCEPTION_FORMAT = "value can not be calculated due {0}";
        private static string TAIL_FORMAT = "\n{0}\n";

        private static List<Function> _functions;

        public static void Main(string[] args)
        {
            bool run = true;
            do
            {
                try
                {
                    Init();

                    Console.WriteLine(INVITATION);
                    string input = Console.ReadLine();
                    if (CheckIfExit(input))
                        break;
                    double x = ExtractX(input);

                    //foreach (Function function in _functions)//Task3
                    foreach (IFunction function in _functions)//Task4
                    {
                        string name = function.GetType().Name;
                        string form = function.ToString();
                        string param = ParametersTostring(function.GetParameters());

                        Console.WriteLine(HEADER_FORMAT, name, form,  param, x);

                        try
                        {
                            double value = function.Calculate(x);
                            Console.WriteLine(VALUE_FORMAT, value);
                        }
                        catch (ArithmeticException e)
                        {
                            Console.WriteLine(VALUE_EXCEPTION_FORMAT, e.Message);
                        }

                        Console.WriteLine(TAIL_FORMAT, DelimiterLine);
                    }
                }
                catch (FormatException)
                {
                    Console.WriteLine(NOT_VALID_NUMBER_EXCEPTION);
                }
                catch (ArgumentException e)
                {
                    Console.WriteLine(ILLEGAL_ARGUMENT_EXCEPTION_FORMAT, e.Message);
                }
                catch (IOException e)
                {
                    Console.WriteLine(IO_EXCEPTION_FORMAT, e.Message);
                    run = false;
                }
            } while (run);
        }

        private static bool CheckIfExit(string input)
        {
            return input.ToLower().Contains(EXIT_COMMAND);
        }

        private static string ParametersTostring(Dictionary<string, Double> parameters)
        {
            return string.Join("\n", parameters.Select(x => string.Format(PARAM_FORMAT, x.Key, x.Value)));
        }

        private static double ExtractX(string input)
        {
            string[] splitInput = input.Split(' ');
            if (splitInput.Length == 0)
                throw new ArgumentException(EMPTY_PARAMETERS_MESSAGE);

            string xstring = splitInput[0];
            if (!double.TryParse(xstring, out double result))
                throw new ArgumentException(string.Format(WRONG_FIRST_PARAMETER_FORMAT, xstring));

            return result;
        }

        private static void Init()
        {
            _functions = new List<Function>
            {
                new Line(a, b),
                new Kub(a, b, c),
                new Hyperbola(a, b)
            };
        }
    }
}