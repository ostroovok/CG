using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class Grid
    {
        public Size Size { get; private set; }
        public Cell[,] GridArray { get; private set; }
        public int Divider { get; private set; }
        private int lH, lW;
        public Grid(Size size)
        {
            Size = size;
        }
        public Cell GetCell(int r, int c)
        {
            for (int i = 0; i <= GridArray.GetUpperBound(0); i++)
            {
                for (int j = 0; j <= GridArray.GetUpperBound(1); j++)
                {
                    if (i == r && j == c)
                        return GridArray[i, j];
                }
            }
            return null;
        }
        public void UpdateGrid()
        {
            foreach (var v in GridArray)
            {
                v.Stars.Update();
            }
        }
        public void DrawGrid(Graphics g)
        {
            foreach (var v in GridArray)
            {
                v.Stars.Draw(g);
            }
        }
        public void ShowGrid(Graphics g)
        {
            foreach (var v in GridArray)
            {
                g.DrawRectangle(Pens.Red, v.Point.X, v.Point.Y, v.Size.Width, v.Size.Height);
            }
        }
        private void PlusSize(Random rnd)
        {
            Cell[,] newGrid = new Cell[Divider+1, Divider + 1];
            int oldH = Size.Height;
            int oldW = 0;
            for (int i = 0; i < Divider + 1; i++)
            {
                Cell c = new Cell(new Point2D(oldW, oldH), lW, lH);
                c.Stars.Size = c.Size;
                c.Stars.Generate(rnd.Next(2), rnd);
                newGrid[Divider, i] = c;
                oldW += lW;
            }
            oldH = 0;
            oldW = Size.Width;
            for (int i = 0; i < Divider + 1; i++)
            {
                Cell c = new Cell(new Point2D(oldW, oldH), lW, lH);
                c.Stars.Size = c.Size;
                c.Stars.Generate(rnd.Next(2), rnd);
                newGrid[i, Divider] = c;
                oldH += lH;
            }
            for (int i = 0; i < GridArray.GetUpperBound(0) + 1; i++)
            {
                for (int j = 0; j < GridArray.GetUpperBound(1) + 1; j++)
                {
                    newGrid[i, j] = GridArray[i, j];
                }
            }
            GridArray = newGrid;
        }
        private void MinusSize()
        {
            Cell[,] newGrid = new Cell[Divider-1, Divider-1];
            for (int i = 0; i < newGrid.GetUpperBound(0) + 1; i++)
            {
                for (int j = 0; j < newGrid.GetUpperBound(1) + 1; j++)
                {
                    newGrid[i, j] = GridArray[i, j];
                }
            }
            GridArray = newGrid;
        }
        public void UpdateGridSize(Size size)
        {
            var rnd = new Random();
            int dW = size.Width - Size.Width;
            int dH = size.Height - Size.Height;
            //Console.WriteLine($"dW -- {dW} ========  dH -- {dH}");
            if (dW > lW/2 || dH > lH/2)
            {
                PlusSize(rnd);
                Size = new Size(Size.Width + lW, Size.Height + lH);
                Divider += 1;
            }
            else if (dW < 0 && dH < 0 && Math.Abs(dW) > lW && Math.Abs(dH) > lH)
            {
                MinusSize();
                Size = new Size(Size.Width - lW, Size.Height - lH);
                Divider -= 1;
            }
        }
        public int FindDivider(Size size)
        {
            int max = size.Width > size.Height ? size.Width : size.Height;
            for (int i = max; i > 0; i--)
            {
                if (size.Width%i==0 && size.Height % i == 0)
                {
                    return i;
                }
            }
            return -1;
        }
        public void TransformSize()
        {
            if(Size.Width > Size.Height)
            {
                while (FindDivider(Size) <= 1)
                {
                    Size = new Size(Size.Width - 1, Size.Height);
                }
            }
            else
            {
                while (FindDivider(Size) <= 1)
                {
                    Size = new Size(Size.Width, Size.Height - 1);
                }
            }
        }
        public void CreateGrid()
        {
            Divider = FindDivider(Size);
            if (Divider <= 1)
            {
                TransformSize();
                Divider = FindDivider(Size);
            }
            Console.WriteLine(Divider);
            lH = Size.Height / Divider;
            lW = Size.Width / Divider;
            int oldH = 0;
            int oldW = 0;
            GridArray = new Cell[Divider, Divider];
            for (int i = 0; i < Divider; i++)
            {
                for (int j = 0; j < Divider; j++)
                {
                    GridArray[i, j] = new Cell(new Point2D(oldW, oldH), lW, lH);
                    oldW += lW;
                }
                oldW = 0;
                oldH += lH;
            }
        }
    }
}
