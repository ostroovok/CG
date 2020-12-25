using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class Cell
    {
        public Size Size { get; set; }
        public Point2D Point { get; set; }
        public StarCanvas Stars { get; set; }

        public Cell(Point2D point, int width, int height)
        {
            Size = new Size(width, height);
            Point = point;
            Stars = new StarCanvas(new List<Star>(), Point);
        }
        public Cell(Point2D point, Size size)
        {
            Size = size;
            Point = point;
        }
    }
}
