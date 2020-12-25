using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class Star
    {
        public Size Size { get; set; }
        public Point2D Point { get; set; }
        public int Speed { get; set; }
        private bool check;
        public Star(Size size, Point2D point, int speed)
        {
            Size = size;
            Point = point;
            Speed = speed;
        }
        public void Draw(Graphics g)
        {
            g.DrawBezier(Pens.White, new Point((int)Point.X - Size.Width, (int)Point.Y),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X, (int)Point.Y - Size.Height),
                new Point((int)Point.X, (int)Point.Y - Size.Height));
            g.DrawBezier(Pens.White, new Point((int)Point.X + Size.Width, (int)Point.Y),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X, (int)Point.Y + Size.Height),
                new Point((int)Point.X, (int)Point.Y + Size.Height));
            g.DrawBezier(Pens.White, new Point((int)Point.X - Size.Width, (int)Point.Y),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X, (int)Point.Y + Size.Height),
                new Point((int)Point.X, (int)Point.Y + Size.Height));
            g.DrawBezier(Pens.White, new Point((int)Point.X + Size.Width, (int)Point.Y),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X, (int)Point.Y - Size.Height),
                new Point((int)Point.X, (int)Point.Y - Size.Height));

            g.DrawBezier(Pens.White, new Point((int)Point.X - Size.Width/2, (int)Point.Y - Size.Height/2),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X - Size.Width/2, (int)Point.Y + Size.Height/2),
                new Point((int)Point.X - Size.Width/2, (int)Point.Y + Size.Height/2));
            g.DrawBezier(Pens.White, new Point((int)Point.X + Size.Width/2, (int)Point.Y - Size.Height / 2),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X + Size.Width/2, (int)Point.Y + Size.Height/2),
                new Point((int)Point.X + Size.Width/2, (int)Point.Y + Size.Height/2));
            g.DrawBezier(Pens.White, new Point((int)Point.X + Size.Width/2, (int)Point.Y - Size.Height / 2),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X - Size.Width / 2, (int)Point.Y - Size.Height / 2),
                new Point((int)Point.X - Size.Width / 2, (int)Point.Y - Size.Height / 2));
            g.DrawBezier(Pens.White, new Point((int)Point.X - Size.Width / 2, (int)Point.Y + Size.Height / 2),
                new Point((int)Point.X, (int)Point.Y), new Point((int)Point.X + Size.Width / 2, (int)Point.Y + Size.Height / 2),
                new Point((int)Point.X + Size.Width / 2, (int)Point.Y + Size.Height/2));

            g.FillEllipse(new SolidBrush(Color.FromArgb(5, 255, 255, 255)), Point.X - Size.Width*2, Point.Y - Size.Height*2, Size.Width * 4, Size.Height * 4);
            g.FillEllipse(new SolidBrush(Color.FromArgb(35, 255, 255, 255)), Point.X - Size.Width, Point.Y - Size.Height, Size.Width*2, Size.Height*2);
            g.FillEllipse(new SolidBrush(Color.FromArgb(55, 255, 255, 255)), Point.X - Size.Width/2, Point.Y - Size.Height/2, Size.Width, Size.Height);
            g.FillEllipse(new SolidBrush(Color.FromArgb(100, 255, 255, 255)), Point.X - Size.Width/4, Point.Y - Size.Height/4, Size.Width / 2, Size.Height / 2);
            g.DrawLine(Pens.White, Point.X + Size.Width/1.5f, Point.Y + Size.Height/ 1.5f, Point.X - Size.Width/ 1.5f, Point.Y - Size.Height/ 1.5f);
            g.DrawLine(Pens.White, Point.X + Size.Width / 1.5f, Point.Y - Size.Height / 1.5f, Point.X - Size.Width / 1.5f, Point.Y + Size.Height / 1.5f);
        }
        public void Update()
        {
            if(Size.Width >=35 || Size.Height >= 35)
                check = true;
            else if(Size.Width <= 15 || Size.Height <= 15)
                check = false;
            ReSize();
        }
        private void ReSize()
        {
            if (check)
                Size = new Size(Size.Width - Speed, Size.Height - Speed);
            else 
                Size = new Size(Size.Width + Speed, Size.Height + Speed);
        }
    }
}
