using System;

namespace Task4
{
    public class KeyPressCounter
    {
        private int _count;

        public void OnKeyPressed(char key)
        {
            _count++;

            if (key == '.')
                Console.WriteLine("Было нажато {0} клавиш", _count);
        }
    }
}