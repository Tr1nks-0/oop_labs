namespace LR6
{
    public class Client
    {
        private readonly AbstractCar _abstractCar;
        private readonly AbstractEngine _abstractEngine;

        public Client(CarFactory carFactory)
        {
            _abstractCar = carFactory.CreateCar();
            _abstractEngine = carFactory.CreateEngine();
        }

        public void Run()
        {
            _abstractCar.MaxSpeed(_abstractEngine);
        }
    }
}