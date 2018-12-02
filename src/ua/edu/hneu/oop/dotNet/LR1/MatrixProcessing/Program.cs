using System;
using System.Collections.Generic;
using System.Text;

namespace MatrixProcessing
{
    class Program
    {
        private static readonly Random Random = new Random();

        private static readonly int MinElement = 0;
        private static readonly int MaxElement = 1;

        static void Main(string[] args)
        {
            try
            {
                Console.WriteLine("Write first size: ");
                string firstInput = Console.ReadLine();
                int a = int.Parse(firstInput ?? throw new InvalidOperationException());

                string secondInput = Console.ReadLine();
                int b = int.Parse(secondInput ?? throw new InvalidOperationException());

                int[][] originalMatrix = ObtainMatrix(a, b);
                int[][] compactedMatrix = CompactMatrix(originalMatrix, a, b);

                Console.WriteLine("Original: \n {0}", MatrixToString(originalMatrix));
                Console.WriteLine("Compacted: \n {0}", MatrixToString(compactedMatrix));
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Console.WriteLine(e);
            }

            Console.ReadKey();
        }

        public static int[][] ObtainMatrix(int sizeX, int sizeY)
        {
            int[][] matrix = new int[sizeX][];
            for (int i = 0; i < sizeX; i++)
            {
                matrix[i] = new int[sizeY];
                for (int j = 0; j < sizeY; j++)
                    matrix[i][j] = Random.Next(MinElement, MaxElement + 1);
            }
            return matrix;
        }

        public static int[][] CompactMatrix(int[][] matrix, int sizeX, int sizeY)
        {
            List<int> rowIndexes = GetNotNullIndexes(matrix, sizeX, sizeY, true);
            List<int> columnIndexes = GetNotNullIndexes(matrix, sizeY, sizeX, false);

            int[][] result = new int[rowIndexes.Count][];

            for (int i = 0; i < rowIndexes.Count; i++)
            {
                result[i] = new int[columnIndexes.Count];
                for (int j = 0; j < columnIndexes.Count; j++)
                    result[i][j] = matrix[rowIndexes[i]][columnIndexes[j]];
            }

            return result;
        }

        public static string MatrixToString(int[][] matrix)
        {
            StringBuilder builder = new StringBuilder();
            foreach (int[] row in matrix)
                builder.AppendLine(string.Join(" ", row));

            return builder.ToString();
        }

        private static List<int> GetNotNullIndexes(int[][] matrix, int sizeOuter, int sizeInner, bool rowRunningMode)
        {
            List<int> indexes = new List<int>();
            for (int i = 0; i < sizeOuter; i++)
            {
                bool isNotEmpty = false;

                for (int j = 0; j < sizeInner; j++)
                {
                    int element = rowRunningMode ? matrix[i][j] : matrix[j][i];
                    if (element != 0)
                    {
                        isNotEmpty = true;
                        break;
                    }
                }

                if (isNotEmpty)
                    indexes.Add(i);
            }

            return indexes;
        }
    }
}