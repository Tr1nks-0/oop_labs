using System;
using System.Collections.Generic;

namespace Task3
{
    public class Line : Function
    {
        public Line(double a, double b)
        {
            A = a;
            B = b;
        }

        public double A { get; }
        public double B { get; }

        public override double Calculate(double x)
        {
            return A * x + B;
        }

        public override Dictionary<String, Double> GetParameters()
        {
            return new Dictionary<String, Double> {{"a", A}, {"b", B}};
        }

        public override String ToString()
        {
            return "a*x + b";
        }
    }
}