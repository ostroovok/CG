package Taske3Sem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame extends JDialog {
    private JPanel contentPane;
    private Canvas canvas;

    private ArrayList<HouseBody> bodies = new ArrayList<>();
    private ArrayList<Window> windows = new ArrayList<>();
    Point p1 = new Point();
    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics gr) {
            super.paint(gr);
            int w = this.getWidth();
            int h = this.getHeight();
            for(HouseBody body: bodies) {
                body.setPoint(new Point(w,h));
            }

            Graphics2D g2d = (Graphics2D) gr;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawRect(w/8, h/8,w-w/4,h-h/4);
            printObj(g2d);
        }

        void printObj(Graphics g) {
            for(HouseBody body: bodies){
                body.print(g);
            }
            for(Window win: windows){
                win.print(g);
            }
        }
    }

    private Frame() {
        setContentPane(contentPane);
        setModal(true);

        contentPane.setLayout(new BorderLayout());
        canvas = new Canvas();
        contentPane.add(new JScrollPane(canvas));
        contentPane.setSize(1200,700);


        HouseBody house = new HouseBody();
        bodies.add(house);
        Window window = new Window(Color.WHITE,house, 200,200,0);
        Window secWindow = new Window(Color.WHITE,house, 200,200, 1);
        windows.add(window);
        windows.add(secWindow);



        this.canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                p1 = e.getPoint();
                repaint();
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
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                int x = e.getX() - p1.x/2;
                int y = e.getY() - p1.y/2;
                for (HouseBody body : bodies) {
                    if (body.connectShape(p1)) {
                        body.setOldP1();
                        body.setOfPoint(new Point(x, y));
                    }
                }
                p1 = e.getPoint();
                canvas.repaint();
            }
        });
    }

    public static void main(String[] args) {
        Frame dialog = new Frame();
        dialog.setSize(1200, 700);
        dialog.setVisible(true);
        System.exit(0);
    }
}
