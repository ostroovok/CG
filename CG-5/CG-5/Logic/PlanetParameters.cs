using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class PlanetParameters
    {
        public int Length { get; set; }
        public int Radius { get; set; }
        public double Angle { get; set; }
        public Color Color { get; set; }
        public double Speed { get; set; }
        public Point2D Point { get; set; }
        public PlanetParameters(int length, int radius, double angle, Color color, double speed, Point2D point)
        {
            Length = length;
            Radius = radius;
            Angle = angle;
            Color = color;
            Speed = speed;
            Point = point;
        }
    }
}
