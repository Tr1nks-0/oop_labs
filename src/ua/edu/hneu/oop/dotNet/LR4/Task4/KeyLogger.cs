using System;

namespace Task4
{
    public class KeyLogger
    {
        public void OnKeyPressed(char key)
        {
            Console.WriteLine("Получено сообщение о нажатии клавиши: {0}", key);
        }

    }
}