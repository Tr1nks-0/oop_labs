using System;
using System.Collections.Generic;
using System.Linq;

namespace FunctionValueCaculator.Engine
{
    public class ComplexFunction : FunctionBase
    {
        private readonly List<FunctionBase> _functions;

        public ComplexFunction()
        {
            //Functions are hardcoded
            _functions = new List<FunctionBase>
            {
                new SimpleFunction(arg => -Math.Sqrt(-(arg * arg) - 16 * arg - 60) + 2, -10, -6),
                new SimpleFunction(arg => 2, -6, -4),
                new SimpleFunction(arg => -0.5 * arg, -4, 2),
                new SimpleFunction(arg => arg - 3, 2, 4)
            };
        }

        public override double LowBound => _functions.Min(x => x.LowBound);

        public override double HighBound => _functions.Max(x => x.HighBound);

        protected override double CalculateValue(double argument)
        {
            FunctionBase function = _functions.First(x => x.CanCalculate(argument));

            return function.Calculate(argument);
        }

        public override bool CanCalculate(double argument)
        {
            return _functions.First(x => x.CanCalculate(argument)) != null;
        }
    }
}