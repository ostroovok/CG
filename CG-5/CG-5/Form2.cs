using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;

using System.Windows.Forms;

namespace CG_5
{
    public partial class Form2 : Form
    {
        private SolarSystem FirstSystem;
        private Grid grid;
        private Bitmap myBitmap;
        private Graphics g;
        private bool showGrid = false;
        private bool isClicked = false;
        private TextBox tb = new TextBox();
        public Form2()
        {
            InitializeComponent();
            Load += Form2_Load;
            Paint += Form2_Paint;
            PropertyInfo db = typeof(Control).GetProperty("DoubleBuffered", BindingFlags.NonPublic |
               BindingFlags.Instance);
            db.SetValue(pictureBox1, true);
            CreateStarGrid();
            
            
            Form f2 = new Form();
            f2.Show();

            Button b1 = new Button();
            b1.Location = new Point(60, 25);
            b1.Text = "Random Double System";
            b1.Size = new Size(f2.Size.Width / 2, 30);
            b1.Click += b1_Click;

            Button b2 = new Button();
            b2.Location = new Point(60, 75);
            b2.Text = "Random Single System";
            b2.Size = new Size(f2.Size.Width / 2, 30);
            b2.Click += b2_Click;

            Button b3 = new Button();
            b3.Location = new Point(60, 125);
            b3.Text = "Example Solar System";
            b3.Size = new Size(f2.Size.Width / 2, 30);
            b3.Click += b3_Click;

            Button b4 = new Button();
            b4.Location = new Point(60, 175);
            b4.Text = "Grid On/Off";
            b4.Size = new Size(f2.Size.Width / 2, 30);
            b4.Click += b4_Click;

            tb.Location = new Point(60, 225);

            f2.Controls.Add(tb);
            f2.Controls.Add(b1);
            f2.Controls.Add(b2);
            f2.Controls.Add(b3);
            f2.Controls.Add(b4);
        }
        private void ShowDialogMessage()
        {
            MessageBox.Show("TextBox is Empty / Non-integer Values", "ERORR",
                MessageBoxButtons.OK,
                MessageBoxIcon.Error,
                MessageBoxDefaultButton.Button1,
                MessageBoxOptions.DefaultDesktopOnly);
        }
        private void b1_Click(object sender, EventArgs e) 
        {
            if (tb.Text == "")
                ShowDialogMessage();
            else
            {
                FirstSystem = new SolarSystem(new List<Planet>());
                FirstSystem.Size = Size;
                FirstSystem.Generate(int.Parse(tb.Text), true);
            }
        }
        private void b2_Click(object sender, EventArgs e) 
        {
            if (tb.Text == "")
                ShowDialogMessage();
            else
            {
                FirstSystem = new SolarSystem(new List<Planet>());
                FirstSystem.Size = Size;
                FirstSystem.Generate(int.Parse(tb.Text), false);
            }    
        }
        private void b3_Click(object sender, EventArgs e) 
        {
            FirstSystem = new SolarSystem(new List<Planet>());
            FirstSystem.Size = Size;
            FirstSystem.CreateSolarSystem();
        }
        private void b4_Click(object sender, EventArgs e) 
        {
            if(isClicked)
                showGrid = true;
            else
                showGrid = false;
            isClicked = !isClicked;
        }
        private void Form2_Load(object sender, EventArgs e)
        {
            TimerStart();
        }
        private void TimerStart()
        {
            Timer tmr = new Timer();
            tmr.Interval = 30;
            tmr.Tick += Tmr_Tick;
            Timer newTmr = new Timer();
            newTmr.Interval = 500;
            newTmr.Tick += NewTmr_Tick;
            newTmr.Start();
            tmr.Start();
        }
        private void NewTmr_Tick(object sender, EventArgs e)
        {
            grid.UpdateGridSize(Size);
            grid.UpdateGrid();
            Invalidate(true);
        }
        private void Tmr_Tick(object sender, EventArgs e)
        {
            if(FirstSystem != null)
            {
                FirstSystem.Size = Size;
                pictureBox1.Size = Size;
                FirstSystem.UpdateSystem();
                Invalidate(true);
            }
        }
        private void Form2_Paint(object sender, PaintEventArgs e)
        {
            myBitmap = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            g = Graphics.FromImage(myBitmap);
            grid.DrawGrid(g);
            if (showGrid)
                grid.ShowGrid(g);
            if (FirstSystem != null)
                FirstSystem.DrawSystem(g);
            pictureBox1.Image = myBitmap;
        }
        private void CreateStarGrid()
        {
            var rnd = new Random();
            grid = new Grid(Size);
            grid.CreateGrid();
            foreach(Cell c in grid.GridArray)
            {
                if(c != null)
                {
                    c.Stars.Size = c.Size;
                    c.Stars.Generate(rnd.Next(2), rnd);
                }
            }
        }
    }
}

