using System;
using System.Collections.Generic;

namespace Task3
{
    public class Kub : Function
    {
        public Kub(double a, double b, double c)
        {
            A = a;
            B = b;
            C = c;
        }

        public double A { get; }
        public double B { get; }
        public double C { get; }

        public override double Calculate(double x)
        {
            return A * x * x + B * x + C;
        }

        public override Dictionary<String, Double> GetParameters()
        {
            return new Dictionary<String, Double> {{"a", A}, {"b", B}, {"c", C}};
        }

        public override String ToString()
        {
            return "a*x*x + b*x + c";
        }
    }
}