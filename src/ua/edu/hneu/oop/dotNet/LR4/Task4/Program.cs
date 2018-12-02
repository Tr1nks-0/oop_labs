using System;

namespace Task4
{
    class Program
    {
        static void Main(string[] args)
        {
            KeyLogger logger = new KeyLogger();
            KeyPressCounter counter = new KeyPressCounter();
            KeyEventArgs keyEventArgs = logger.OnKeyPressed;
            keyEventArgs += counter.OnKeyPressed;

            Console.WriteLine("Введите несколько символов. Для останова введите точку.");
            char c;
            do
            {
                c = Console.ReadKey().KeyChar;
                Console.WriteLine();
                keyEventArgs(c);
            } while (c != '.');

            Console.ReadKey();
        }
    }
}
