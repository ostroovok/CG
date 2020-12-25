using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CG_5
{
    public class SolarSystem
    {
        public Planet Planet { get; set; }
        public List<Planet> Planets { get; set; }
        public Size Size { get; set; }
        public List<Brush> Colors { get; set; }
        public SolarSystem(List<Planet> planets)
        {
            Planets = planets;
        }
        public void Add(Planet p)
        {
            Planets.Add(p);
        }
        public void Remove(Planet p)
        {
            Planets.Remove(p);
        }
        public void DrawSystem(Graphics g)
        {
            foreach (Planet p in Planets)
            {
                p.ObjLocation();
                p.Draw(g);
            }
        }
        public void UpdateSystem()
        {
            Planets[0].Point = new Point2D(Size.Width / 2, Size.Height / 2);
            foreach (Planet p in Planets)
            {
                p.Update(1000);
            }
        }
        public void Generate(int num, bool doubleSystem)
        {
            Random rand = new Random();
            Planet p;
            if (doubleSystem)
            {
                Planet parent = new Planet(0, Color.Yellow, new Point2D(Size.Width / 2, Size.Height / 2));
                Planets.Add(parent);
                Color[] colors = GetRandomColor(num-2);
                int l = 10;
                for (int i = 0; i < num; i++)
                {
                    if (i < 2)
                        Planets.Add(new Planet(parent, 50, rand.Next(25, 50), i * 10, Color.Cyan, 15, 0.7));
                    else 
                    {
                        p = new Planet(parent,l, rand.Next(10, 25), (float)rand.NextDouble(), colors[i - 2], rand.NextDouble() * 30 + 1, 1);

                        if (rand.NextDouble() >= 0.5 && i > 3)
                        {
                            Planets.Add(p);
                            Planets.Add(new Planet(p, rand.Next((int)parent.Radius + 25, (int)parent.Radius + 50),
                                                        rand.Next(2, 5), (float)rand.NextDouble(), colors[i - 2], rand.NextDouble() * 50 + 1, 1));
                        }  
                        else
                            Planets.Add(p);
                    }
                    l += 50;
                }
            }
            else
            {
                int l = 50;
                Planet parent = new Planet(50, Color.Yellow, new Point2D(Size.Width/2, Size.Height/2));
                Planets.Add(parent);
                Color[] colors = GetRandomColor(num-1);
                for (int i = 1; i < num; i++)
                {
                    p = new Planet(parent, (int)parent.Radius + l, rand.Next(10, 25), (float)rand.NextDouble(), colors[i - 1], rand.NextDouble() * 30 + 1, 1);

                    if (rand.NextDouble() >= 0.5 && i > 3)
                    {
                        Planets.Add(p);
                        Planets.Add(new Planet(p, rand.Next((int)p.Radius + 5, (int)p.Radius + 10),
                                                    rand.Next(2, 5), (float)rand.NextDouble(), colors[i - 1], rand.NextDouble() * 50 + 1, 1));
                    }
                    else
                        Planets.Add(p);
                    l += 50;
                }
            }
        }

        public void CreateSolarSystem()
        {
            Planet sun = new Planet(60, Color.Yellow, new Point2D(Size.Width / 2, Size.Height / 2));
            Planet mercury = new Planet(sun, 80, 8, 0.5f, Color.Red, 10, 1);
            Planet venus = new Planet(sun, 110, 9, 0.1f, Color.DarkOrange, 15, 1);
            Planet earth = new Planet(sun, 150, 12, 0.2f, Color.Green, 18, 1);
                Planet moon = new Planet(earth, 17, 3, 0.2f, Color.Gray, 55, 1);
            Planet mars = new Planet(sun, 225, 10, 0.7f, Color.Red, 17, 1);
            Planet upiter = new Planet(sun, 275, 25, 0.9f, Color.RosyBrown, 13, 1);
                Planet cerera = new Planet(upiter, 45, 6, 0.2f, Color.Gray, 45, 1);
            Planet saturn = new Planet(sun, 350, 45, 0.3f, Color.SandyBrown, 10, 1);
                Planet titan = new Planet(saturn, 56, 7, 0.2f, Color.BlueViolet, 35, 1);
            Planet uran = new Planet(sun, 435, 20, 0.9f, Color.Cyan, 12, 1);
            Planet neptune = new Planet(sun, 500, 18, 0.4f, Color.Blue, 7, 1);
            Planets.Add(sun);
            Planets.Add(mercury);
            Planets.Add(venus);
            Planets.Add(earth);
                Planets.Add(moon);
            Planets.Add(mars);
            Planets.Add(upiter);
                Planets.Add(cerera);
            Planets.Add(saturn);
                Planets.Add(titan);
            Planets.Add(uran);
            Planets.Add(neptune);
        }
        private Color[] GetRandomColor(int num)
        {
            Random rand = new Random();
            List<Color> l = new List<Color>();
            for(int i = 0; i < num; i++)
            {
                l.Add(Color.FromArgb(rand.Next(255), rand.Next(255), rand.Next(255)));
            }
            return l.ToArray();
        }
    }
}
