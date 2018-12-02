using System;
using System.Text;

namespace Task2
{
    public class Matrix
    {
        private const string NegativeDimensionMessage = "Dimensions of matrix must be not negative.";

        private readonly double[,] _matrix;

        /// <summary>
        /// Matrix N x M
        /// </summary>
        /// <param name="rowCount">Row count</param>
        /// <param name="columnCount">Column count</param>
        public Matrix(int rowCount, int columnCount)
        {
            ThrowIfMatrixDimensionsAreInvalid(rowCount, columnCount);

            RowCount = rowCount;
            ColumnCount = columnCount;

            _matrix = new double[RowCount, ColumnCount];
        }

        /// <summary>
        /// Square mtrix
        /// </summary>
        /// <param name="dimensionSize"></param>
        public Matrix(int dimensionSize)
        {
            ThrowIfMatrixDimensionsAreInvalid(dimensionSize, dimensionSize);

            _matrix = new double[dimensionSize, dimensionSize];
            RowCount = ColumnCount = dimensionSize;
        }

        private void ThrowIfMatrixDimensionsAreInvalid(int rowCount, int columnCount)
        {
            if (rowCount < 0)
                throw new ArgumentException(NegativeDimensionMessage);

            if (columnCount < 0)
                throw new ArgumentException(NegativeDimensionMessage);

            if (columnCount == 0 && rowCount == 0)
                throw new InvalidOperationException("Cannot create matrix with 0x0 elements.");
        }

        public int RowCount { get; }

        public int ColumnCount { get; }


        public bool IsSquare => RowCount == ColumnCount;

        public bool CheckIsZeroMatrix()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (this[i, j] != 0.0)
                        return false;
                }
            }
            return true;
        }

        public bool CheckIsDiagonal()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (i == j)
                    {
                        if (this[i, j] == 0.0)
                            return false;
                    }
                    else
                    {
                        if (this[i, j] != 0.0)
                            return false;
                    }

                }
            }
            return true;
        }

        public bool CheckIsUnitMatrix()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (i == j)
                    {
                        if (this[i, j] != 1.0)
                            return false;
                    }
                    else
                    {
                        if (this[i, j] != 0.0)
                            return false;
                    }

                }
            }
            return true;
        }

        public bool CheckIsSimetric()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (i == j)
                        continue;

                    if (this[i, j] != this[j,i])
                        return false;
                }
            }

            return true;
        }

        public bool CheckIsTopTriangle()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (i > j)
                        continue;

                    if (this[i, j] == 0.0)
                        return false;
                }
            }

            return true;
        }

        public bool CheckIsLowTriangle()
        {
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                {
                    if (i < j)
                        continue;

                    if (this[i, j] == 0.0)
                        return false;
                }
            }

            return true;
        }

        public override string ToString()
        {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < RowCount; i++)
            {
                for (int j = 0; j < ColumnCount; j++)
                    str.AppendFormat("{0}  ", this[i, j]);
                str.AppendLine();
            }

            return str.ToString();
        }

        public double this[int i, int j]
        {
            get => _matrix[i, j];
            set => _matrix[i, j] = value;
        }

        public static Matrix operator +(Matrix first, Matrix second)
        {
            Matrix mat = new Matrix(first.RowCount, first.ColumnCount);
            for (int i = 0; i < first.RowCount; i++)
                for (int j = 0; j < first.ColumnCount; j++)
                    mat[i, j] = first[i, j] + second[i, j];
            return mat;
        }

        public static Matrix operator +(Matrix first, int t)
        {
            Matrix restult = new Matrix(first.RowCount, first.ColumnCount);

            for (int i = 0; i < first.RowCount; i++)
            {
                for (int j = 0; j < first.ColumnCount; j++)
                    restult[i, j] = first[i, j] + t;
            }

            return restult;
        }

        public static Matrix operator -(Matrix first, Matrix second)
        {
            Matrix mat = new Matrix(first.RowCount, first.ColumnCount);
            for (int i = 0; i < first.RowCount; i++)
                for (int j = 0; j < first.ColumnCount; j++)
                    mat[i, j] = first[i, j] - second[i, j];
            return mat;
        }

        public static Matrix operator *(Matrix m, int t)
        {
            Matrix mat = new Matrix(m.RowCount, m.ColumnCount);
            for (int i = 0; i < m.RowCount; i++)
                for (int j = 0; j < m.ColumnCount; j++)
                    mat[i, j] = m[i, j] * t;
            return mat;
        }

        public static Matrix operator *(Matrix first, Matrix second)
        {
            Matrix result = new Matrix(first.RowCount, first.ColumnCount);
            for (int i = 0; i < first.RowCount; i++)
            {
                for (int j = 0; j < second.ColumnCount; j++)
                {
                    double sum = 0;
                    for (int r = 0; r < first.ColumnCount; r++)
                        sum += first[i, r] * second[r, j];
                    result[i, j] = sum;
                }
            }
            return result;
        }

        public static Matrix operator ^(Matrix first, int pow)
        {
            Matrix result = first;
            for (int z = 1; z < pow; z++)
            {
                Matrix bufer = new Matrix(first.RowCount, first.ColumnCount);
                for (int i = 0; i < first.RowCount; i++)
                {
                    for (int j = 0; j < first.RowCount; j++)
                    {
                        double sum = 0;
                        for (int r = 0; r < first.RowCount; r++)
                            sum += result[i, r] * first[r, j];
                        bufer[i, j] = sum;
                    }
                }
                result = bufer;
            }

            return result;
        }
    }
}
