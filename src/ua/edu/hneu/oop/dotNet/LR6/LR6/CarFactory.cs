using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LR6
{
    public abstract class CarFactory
    {
        public abstract AbstractCar CreateCar();
        public abstract AbstractEngine CreateEngine();
    }

    class AudiFactory : CarFactory
    {
        public override AbstractCar CreateCar()
        {
            return new AudiCar();
        }

        public override AbstractEngine CreateEngine()
        {
            return new AudiEngine();
        }
    }

    class BMWFactory : CarFactory
    {
        public override AbstractCar CreateCar()
        {
            return new BMWCar();
        }

        public override AbstractEngine CreateEngine()
        {
            return new BMWEngine();
        }
    }
}
