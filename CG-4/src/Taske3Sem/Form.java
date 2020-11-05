package Taske3Sem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

public class Form extends JFrame{

    private LinkedList<TakeBall> balls = new LinkedList<>();
    private JPanel contentPane;
    private JPanel canvasPane;
    private JRadioButton threeBut;
    private JRadioButton fourBut;
    private JPanel controlPane;
    Canvas canvas;
    Point p1 = new Point();

    private class Canvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
            printObj(g2d);
        }

        public void printObj(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).paint(g,i+1);
            }
            if(threeBut.isSelected()) {
                BezierCurve curve = new BezierCurve(balls.get(0).getPoint(), balls.get(1).getPoint(),
                        balls.get(2).getPoint(), 0.00005);
                curve.drawCurve(g2d);
            }
            if(fourBut.isSelected()) {
                BezierCurve curve = new BezierCurve(balls.get(0).getPoint(), balls.get(1).getPoint(),
                        balls.get(2).getPoint(), balls.get(3).getPoint(), 0.00005);
                curve.drawCurve(g2d);
            }
        }
    }


    public Form(){
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(1000,800));
        //contentPane.setSize(1200,700);
        canvasPane.setLayout(new BorderLayout());
        canvas = new Canvas();
        canvasPane.add(new JScrollPane(canvas));
        //balls.add(new TakeBall());
        balls.add(new TakeBall(new Point(90,390),40));
        balls.add(new TakeBall(new Point(290,90),40));
        balls.add(new TakeBall(new Point(490,390),40));
        balls.add(new TakeBall(new Point(490,90),40));

        ButtonGroup group = new ButtonGroup();
        group.add(threeBut);
        group.add(fourBut);


        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                p1 = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                int dx = p1.x - e.getX();
                int dy = p1.y - e.getY();
                for (TakeBall ball : balls) {
                    if (ball.connectShape(p1)) {
                        int x = p1.x + dx/10;
                        int y = p1.y + dy/10;
                        ball.setPoint(new Point(x-ball.getSize()/2,y-ball.getSize()/2));
                        break;
                    }
                }
                p1 = e.getPoint();
                canvas.repaint();
            }
        });
    }
}
