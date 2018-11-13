using System;
using System.Collections.Generic;

namespace Task3
{
    public class Hyperbola : Function
    {
        public Hyperbola(double a, double b)
        {
            A = a;
            B = b;
        }

        public double A { get; }
        public double B { get; }


        public override double Calculate(double x)
        {
            if (x == 0)
                throw new ArithmeticException("division by zero");

            return A / x + B;
        }

        public override Dictionary<String, Double> GetParameters()
        {
            return new Dictionary<String, Double> {{"a", A}, {"b", B}};
        }

        public override String ToString()
        {
            return "(a/x) + b";
        }
    }
}