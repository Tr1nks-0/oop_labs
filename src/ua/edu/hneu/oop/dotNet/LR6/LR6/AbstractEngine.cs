namespace LR6
{
    public abstract class AbstractEngine
    {
        public int max_speed;
    }

    class AudiEngine : AbstractEngine
    {
        public AudiEngine()
        {
            max_speed = 300;
        }
    }

    class BMWEngine : AbstractEngine
    {
        public BMWEngine()
        {
            max_speed = 200;
        }
    }
}