using System;
using System.Collections.Generic;

namespace Task3
{
    public abstract class Function : IFunction
    {
        public abstract double Calculate(double x);

        public abstract Dictionary<String, Double> GetParameters();
    }
}