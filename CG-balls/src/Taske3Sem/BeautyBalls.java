package Taske3Sem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

class BeautyBalls extends JComponent implements MouseMotionListener {

    private ArrayList<Ball> all = new ArrayList<>();
    private int width = 1000;
    private int height = 1000;

    public BeautyBalls() {
        int dx = 0, dy = 0;
        int x = 25, y = 25;
        int da = 0;

        for (int i = 1; i < height/10; i++) {
            for (int j = 0; j < width/10; j++) {
                all.add(new Ball(new Point(x+dx,y+dy),200,10,da,20));
                dx += width/10;
                da += 1;
            }
            dx = 0;
            dy += height/10;

        }
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < all.size(); i++){
                    all.get(i).update(0);
                }
                repaint();
            }
        });
        timer.start();


    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        for(Ball ball: all){
            ball.paint(g2d);
        }
    }

    private Shape circle(double x, double y, double r) {
        return new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Balls");
                frame.add(new BeautyBalls());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 1000);
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
