using System;

namespace FunctionValueCaculator.Engine
{
    public class SimpleFunction : FunctionBase
    {
        private readonly Func<double, double> _calculationFunc;

        public SimpleFunction(Func<double, double> calculationFunc, double lowBound, double highBound)
        {
            if (lowBound > highBound)
            {
                throw new ApplicationException(
                    $"Value of {nameof(lowBound)} must be greater that value of {nameof(highBound)}");
            }

            _calculationFunc = calculationFunc ?? throw new ArgumentNullException(nameof(calculationFunc));

            LowBound = lowBound;
            HighBound = highBound;
        }

        public override double LowBound { get; }
        public override double HighBound { get; }

        protected override double CalculateValue(double argument)
        {
            return _calculationFunc(argument);
        }

        protected override bool CanCalculate(double argument)
        {
            return argument >= LowBound && argument < HighBound;
        }
    }
}