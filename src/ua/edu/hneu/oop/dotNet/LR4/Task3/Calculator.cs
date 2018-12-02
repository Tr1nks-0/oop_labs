using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;

using static Task3.MathOperations;

namespace Task3
{
    public static class Calculator
    {
        private class FunctionInfo
        {
            public FunctionInfo(int priority, Func<double, double, double> function)
            {
                Priority = priority;
                Function = function;
            }

            public int Priority { get; }

            public Func<double, double, double> Function { get; }
        }

        private static readonly Dictionary<char, FunctionInfo> SignToFunctionMap = new Dictionary<char, FunctionInfo>
        {
            {'+', new FunctionInfo(2, Add)},
            {'-', new FunctionInfo(2, Remove)},
            {'*', new FunctionInfo(1, Multiply)},
            {'/', new FunctionInfo(1, Divide)},
            {'^', new FunctionInfo(0, Pow)}
        };

        public static double Calculate(string statement)
        {
            List<string> chunks = SplitAndKeepOperators(statement, SignToFunctionMap.Keys.ToArray())
                .Where(x => !string.IsNullOrEmpty(x))
                .ToList();

            var functions = 
                SignToFunctionMap.
                OrderBy(x => x.Value.Priority).
                Select(x => new { Operator = x.Key.ToString(), Function = x.Value }).
                ToList();

            while (chunks.Count != 1 && chunks.Count !=2)
            {
                var function = functions[0];
                int indexOf = chunks.LastIndexOf(function.Operator);
                if (indexOf == -1)
                    functions.Remove(function);
                else
                {
                    int indexOfFirstOperand = indexOf - 1;

                    double first = double.Parse(indexOfFirstOperand - 1 >= 0 && chunks[indexOfFirstOperand - 1] == "-" ? "-" + chunks[indexOfFirstOperand] : chunks[indexOfFirstOperand]);
                    double second = double.Parse(chunks[indexOf + 1]);

                    double result = function.Function.Function(first, second);

                    if (result < 0)
                    {
                        if (indexOfFirstOperand - 1 >= 0)
                            chunks[indexOfFirstOperand - 1] = "-";
                    }
                    else
                    {
                        if (indexOfFirstOperand - 1 >= 0)
                            chunks[indexOfFirstOperand - 1] = "+";
                    }

                    chunks[indexOfFirstOperand] = chunks.Count == 3 ? result.ToString(CultureInfo.InvariantCulture) : Math.Abs(result).ToString(CultureInfo.InvariantCulture);

                    chunks.RemoveAt(indexOfFirstOperand + 1);//Remove operator
                    chunks.RemoveAt(indexOfFirstOperand + 1);//Remove operand
                }
            }

            return double.Parse(chunks.Count == 2 ? chunks[0] + chunks[1] : chunks[0]);
        }

        public static IEnumerable<string> SplitAndKeepOperators(string statement, IEnumerable<char> separators)
        {
            string[] obj = statement.Split(separators.ToArray());

            int currentPosition = 0;
            for (var i = 0; i < obj.Length; i++)
            {
                string operand = obj[i];
                yield return operand;

                if (i == obj.Length - 1)
                    yield break;

                currentPosition += operand.Length;
                yield return statement.Substring(currentPosition++, 1);
            }
        }
    }
}