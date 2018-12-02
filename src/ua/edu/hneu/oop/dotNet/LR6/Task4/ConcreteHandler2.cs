﻿using System;

namespace Task4
{
    public class ConcreteHandler2 : Handler
    {
        public override void HandleRequest(int request)
        {
            if (request == 2)
                Console.WriteLine("Two");
            else
            {
                Successor?.HandleRequest(request);
            }
        }
    }
}