namespace Task1
{
    public struct Marsh
    {
        public string StartPoint { get; set; }

        public string EndPoint { get; set; }

        public int Number { get; set; }

        public override string ToString()
        {
            return $"Start point: {StartPoint}\nEnd point: {EndPoint}\nMarsh number: {Number}";
        }
    }
}