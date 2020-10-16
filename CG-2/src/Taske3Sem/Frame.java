package Taske3Sem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JDialog {
    private JPanel contentPane;
    private JPanel canvasPane;
    private JRadioButton Xiaolin;
    private JRadioButton DDA;
    private JRadioButton Bresenham;
    private JRadioButton CircleButton;
    private JRadioButton LineButton;
    private JButton ChangeAlg;
    private JButton paintAlgLine;
    private Canvas canvas;

    private Point p1;

    public Frame() {
        setContentPane(contentPane);
        setModal(true);
        canvas = check();//new Canvas("");
        canvasPane.setLayout(new BorderLayout());
        canvasPane.add(new JScrollPane(canvas));
        ButtonGroup gr1 = new ButtonGroup();
        gr1.add(Xiaolin);
        gr1.add(DDA);
        gr1.add(Bresenham);

        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                p1 = e.getPoint();
            }
        });
        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                int x; int y;
                if(LineButton.isSelected()){
                    x = e.getX() - p1.x/2;
                    y = e.getY() - p1.y/2;
                }else{
                    x = e.getX();// - p1.x/2;
                    y = e.getY();// - p1.y/2;
                }
                canvas.setPoints(p1,new Point(x,y));
                if(!CircleButton.isSelected()){
                    p1 = e.getPoint();
                }
                canvas.repaint();
            }
        });
        ChangeAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        paintAlgLine.addActionListener(new ActionListener() {
            private int dy = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setPoints(new Point(150, 125+dy), new Point(450,225+dy));
                dy+=75;
                canvas.repaint();
            }
        });
    }
    private Canvas check(){
        if(Xiaolin.isSelected()){
            return new Canvas("Xiaolin");
        }else if(DDA.isSelected()){
            return new Canvas("DDA");
        }else{
            return new Canvas("Bresenham");
        }
    }

    private static class Canvas extends JPanel {
        private XiaolinLine xLine = new XiaolinLine();
        private static String name;
        private Point p1;
        private Point p2;
        public Canvas(String name){
            Canvas.name = name;
        }
        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if(name.equals("Xiaolin")){
                StartXiaolin(g2d);
            }else if(name.equals("Bresenham")){
                StartBresenham(g2d);
            }else if(name.equals("DDA")){
                StartDDA(g2d);
            }
        }
        private void StartXiaolin(Graphics2D g2d){
            if(p1 != null && p2 != null){
                xLine.drawLine(g2d,p1.x,p1.y,p2.x,p2.y);
            }
        }
        private void StartDDA(Graphics2D g2d){
            DDA(g2d, p1, p2);
        }
        private void StartBresenham(Graphics2D g2d){
            Bresenham(g2d, p1, p2);
        }
        private void setPoints(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    private static void DDA(Graphics2D g2d, Point p1, Point p2){

        if(p1 != null && p2 != null) {
            int x1 = Math.round(p1.x);
            int y1 = Math.round(p1.y);
            int x2 = Math.round(p2.x);
            int y2 = Math.round(p2.y);

            int deltaX = Math.abs(x1 - x2);
            int deltaY = Math.abs(y1 - y2);

            int length = Math.max(deltaX, deltaY);

            double dx = (p2.x - p1.x) * 1.0 / length;
            double dy = (p2.y - p1.y) * 1.0 / length;

            double x = p1.x;
            double y = p1.y;

            //length++;
            while (length > 0) {
                x += dx;
                y += dy;
                g2d.fillRect((int) x, (int) y, 1, 1);
                length--;
            }
        }
    }

    public static void Bresenham(Graphics2D g2d, Point p1, Point p2){
        if(p1 != null && p2 != null) {
            int deltaX = Math.abs(p2.x - p1.x);
            int deltaY = Math.abs(p2.y - p1.y);
            int incX = sign(deltaX);
            int incY = sign(deltaY);

            int pdx, pdy, es, err, el;

            if (deltaX > deltaY) { // горизонталь
                pdx = incX;
                pdy = 0;
                es = deltaY;
                el = deltaX;
            } else { // вертикаль
                pdx = 0;
                pdy = incY;
                es = deltaX;
                el = deltaY;
            }
            int x = p1.x;
            int y = p1.y;
            err = el / 2;
            g2d.fillRect(x, y, 1, 1);
            for (int i = 0; i < el; i++) {
                err -= es;
                if (err < 0) { // сдвинуть
                    err += el;
                    x += incX;
                    y += incY;
                } else { // тянуть
                    x += pdx;
                    y += pdy;
                }
                g2d.fillRect(x, y, 1, 1);
            }
        }
    }

    private static int sign(int x) {
        return Integer.compare(x, 0);
    }

    public static void main(String[] args) {
        Frame dialog = new Frame();
        dialog.setSize(1000,700);
        dialog.setVisible(true);
        System.exit(0);
    }
}
