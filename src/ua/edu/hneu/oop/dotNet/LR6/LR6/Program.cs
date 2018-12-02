using System;

namespace LR6
{
    class Program
    {
        static void Main(string[] args)
        {
            // Абстрактная фабрика № 1 
            CarFactory bmw_car = new BMWFactory ();
            // Абстрактная фабрика № 2       
            CarFactory audi_car = new AudiFactory();

            Client c1 = new Client(bmw_car);
            c1.Run();
            Client c2 = new Client(audi_car);
            c2.Run();

            Console.ReadKey();
        }
    }
}