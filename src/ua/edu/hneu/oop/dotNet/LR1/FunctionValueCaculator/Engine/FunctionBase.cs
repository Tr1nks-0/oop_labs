using System;

namespace FunctionValueCaculator.Engine
{
    public abstract class FunctionBase : IFunction
    {
        public double Calculate(double argument)
        {
            if (!CanCalculate(argument))
                throw GetArgumentOutOfRangeException();

            return CalculateValue(argument);
        }

        public abstract double LowBound { get; }

        public abstract double HighBound { get; }

        public abstract bool CanCalculate(double argument);

        protected abstract double CalculateValue(double argument);


        protected ArgumentOutOfRangeException GetArgumentOutOfRangeException()
        {
            return new ArgumentOutOfRangeException($"Value must be in range between {LowBound} inclusive, and {HighBound} exclusive.");
        }
    }
}