using System;

namespace Task3
{
    public static class MathOperations
    {
        public static double Add(double a, double b) => a + b;

        public static double Remove(double a, double b) => a - b;

        public static double Multiply(double a, double b) => a * b;

        public static double Divide(double a, double b) => a / b;

        public static double Pow(double a, double b) => Math.Pow(a, b);
    }
}