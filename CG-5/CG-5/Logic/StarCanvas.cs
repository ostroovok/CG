using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class StarCanvas 
    {
        public List<Star> Stars { get; set; }
        public Star Star { get; set; }
        public Size Size { get; set; }
        private Size oldSize;
        public Point2D Point { get; set; }
        public StarCanvas(List<Star> stars)
        {
            Stars = stars;
        }
        public StarCanvas(List<Star> stars, Point2D p)
        {
            Point = p;
            Stars = stars;
        }
        public void Add(Star st)
        {
            Stars.Add(st);
        }
        public void Remove(Star st)
        {
            Stars.Remove(st);
        }
        public void Generate(int num, Random rnd)
        {
            if (num == -1)
                num = Size.Width / 25;
            
            for(int i = 0; i < num; i++)
            {
                double x = rnd.Next((int)Point.X, (int)Point.X + Size.Width);
                double y = rnd.Next((int)Point.Y, (int)Point.Y + Size.Height);
                int s = rnd.Next(4, 8);
                Stars.Add(new Star(new Size(s, s), new Point2D((float)x,(float)y), rnd.Next(1,2)));
            }
            for (int i = 0; i < Size.Width/25; i++)
            {
                double x = rnd.Next((int)Point.X, (int)Point.X + Size.Width);
                double y = rnd.Next((int)Point.Y, (int)Point.Y + Size.Height);
                int s = rnd.Next(1, 3);
                Stars.Add(new Star(new Size(s, s), new Point2D((float)x,(float)y), 0));
            }
        }
        public void Update()
        {
            foreach(var v in Stars)
            {
                v.Update();
            }
        }
        public void Draw(Graphics g)
        {
            foreach (var v in Stars)
            {
                v.Draw(g);
            }
        }
    }
}
