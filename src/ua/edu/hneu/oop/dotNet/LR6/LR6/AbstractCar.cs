using System;

namespace LR6
{
    public abstract class AbstractCar
    {
        public abstract void MaxSpeed(AbstractEngine engine);
    }

    class BMWCar : AbstractCar
    {
        public override void MaxSpeed(AbstractEngine engine)
        {
            Console.WriteLine("Макcимальная скорость: " + engine.max_speed);
        }
    }

    class AudiCar : AbstractCar
    {
        public override void MaxSpeed(AbstractEngine engine)
        {
            Console.WriteLine("Макcимальная скорость: " + engine.max_speed);
        }
    }
}