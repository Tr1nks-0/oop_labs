using System;
using System.Collections.Generic;

namespace Task3
{
    public interface IFunction
    {
        double Calculate(double x);
        Dictionary<String, Double> GetParameters();
    }
}